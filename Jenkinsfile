/**
   Jenkins Pipeline Project 
   (MULTIBRANCH - MULTIPROJECT - SADE DEPLOY - declarative)

   NOTES: 
     - This pipeline contains the SCM info/url and ignores the parent's 
       scm object. This allows setting up SCM pooling on specific paths,
       only building a project if it has changes (for multi-project
       Git repo).
       (also see notes in the "Checkout" stage and getGitBranchName)
   
     - A requirement for proper multi-project functionality is that the
       Jenkins project name MUST match the project's folder name in the
       Git repository.
*/

// Git URL
def projectGitURL = 'https://gccode.ssc-spc.gc.ca/iitb-dgiit/sds/GOCWebTemplates/JavaTemplates.git'
def projectGitCredsName = ''

//applicationName is derived from Jenkins project name (which is second to last in the full name)
def jobPathElements = currentBuild.fullProjectName.split('/')
def applicationName = jobPathElements[jobPathElements.length >= 2? jobPathElements.length-2: jobPathElements.length-1]

// Project's POM file
def projectPom = applicationName + '/pom.xml'

// Git Branch/Paths to match for changes
def projectGitWatchedPathRegex = applicationName + '/.*'
def projectGitWatchedBranches = [[name: '*/' + env.BRANCH_NAME]]
def projectReleaseBranchRegex = 'master.*'

// Email extension plugin base parameters
def emailextConfig = [
    to: 'pierre.lupien@hrdc-drhc.net', //comma-separated lits of addresses
    from: 'Jenkins-CI <jenkins-ci@jade-build.intra.dev>',
    body: '${SCRIPT, template="groovy-html.template"}', //for details on body: https://wiki.jenkins.io/display/JENKINS/Email-ext+plugin#Email-extplugin-Scriptcontent
    mimeType: 'text/html'    
    ]


def getGitBranchName() {
    
    def branchName = env.BRANCH_NAME
    
    //If this is not run in a MULTIBRANCH pipeline, BRANCH_NAME will not
    //be available. In that case the branch name can be acquired from
    //Git (requires the "Chekcout to matching local branch" option to
    //be enabled in SCM).
    //def git = tool('git')
    //def branchName = sh(script: "'${git}' branch | grep \"\\* \"", returnStdout: true).replace("* ", "").trim()
    
    echo('Detected Git Branch: [' + branchName + ']')

    return branchName
}


pipeline {
    
    agent any; //any: Run on any available agent - agent is same for all stages
               //none: No top-level setting.  Each stage must declare its own agent. Needed for non-blocking input.
    
    options {
        buildDiscarder logRotator(artifactNumToKeepStr: '5', numToKeepStr: '5')
        disableConcurrentBuilds()
        timestamps()
        skipDefaultCheckout() //skip SCM pull before first stage, we'll do our own
    }
    
    tools {
        maven('maven')
        git('git')
    }
    
    triggers {
        //pollSCM('H H/4 * * *') //every 4 hours
        snapshotDependencies()
    }    
    
    stages {
        stage('Checkout from SCM') {
            steps {
                //We won't be using the simple "checkout(scm)" here because we want
                //to setup our own path restriction and that is not supported
                //by the default multibranch git provider.
                //checkout(scm)
                
                checkout([$class: 'GitSCM', 
                    userRemoteConfigs: [[credentialsId: projectGitCredsName, 
                                         url: projectGitURL]],
                    branches: projectGitWatchedBranches, 
                    extensions: [[$class: 'PathRestriction', //SCM poll filter by path
                                    excludedRegions: '', 
                                    includedRegions: projectGitWatchedPathRegex],
                                 [$class: 'LocalBranch',  //Checkout as named local branch, required for our getGitBranchName to work on non-multibranch pipeline
                                    localBranch: '**']]])
            }
        }
        
        stage('Project Artifact Version Check') {
            //Could only use "when branch" in a multibranch build
            //when {branch('master')}
            //Could also use "when expression" for the "if", but we have an "else" and don't want to create two stages for this
            //when { expression {return getGitBranchName().matches(projectReleaseBranchRegex)} }

            steps {
                script {
                    def projectModel = readMavenPom(file: projectPom)
                    
                    if (getGitBranchName().matches(projectReleaseBranchRegex)) {
                        //RELEASE build: make sure our dependencies are not snapshots
                        def mavenDesc = Artifactory.mavenDescriptor()
                        
                        mavenDesc.pomFile = projectPom
                        if (mavenDesc.hasSnapshots()) {
                            currentBuild.result = 'ABORTED'
                            error('Snapshot(s) detected in dependencies.  Based on the branch, this is a release build.  Snapshot dependencies are not allowed.')
                        }
                        
                        // Also make sure our own version is not a snapshot
                        if (projectModel.version.toUpperCase().endsWith('-SNAPSHOT')) {
                            currentBuild.result = 'ABORTED'
                            error('Trying to build a SNAPSHOT project version from a release branch.  Please update the pom.xml')
                        }
                    } else {
                        //NOT a release build: Warn if building a release version.
                        if (!projectModel.version.toUpperCase().endsWith('-SNAPSHOT')) {
                            currentBuild.result = 'UNSTABLE'
                            echo('WARNING: Trying to build RELEASE (ie non-SNAPSHOT) project version from a non-release branch.  Are you sure this is what you want? If not you will want to update the pom.xml.')
                        }
                    }
                } //of script
            } //of steps
        } //of stage
        
        stage('Build and Deploy to Artifactory') {
            steps {
                withMaven(maven: 'maven') {
                    sh(script: "mvn --batch-mode --errors --update-snapshots -Dbuild_number=${BUILD_NUMBER} -f ${applicationName} clean deploy")
                }
            }
        }
    }

    post {
        always { //Always run, regardless of build status
            //archiveArtifacts(artifacts: "${applicationName}/target/*.?ar", allowEmptyArchive: true, fingerprint: true)
            
            junit(testResults: "${applicationName}/target/surefire-reports/TEST-*.xml", allowEmptyResults: true)
            
            emailext(to: emailextConfig.to,
                     from: emailextConfig.from,
                     body: emailextConfig.body,
                     mimeType: emailextConfig.mimeType,
                     subject: "Jenkins Build [${currentBuild.fullDisplayName}] Built - [${currentBuild.result}] for application [${applicationName}]")
        }
        regression { //Run if the current builds status is worse than the previous builds status
            emailext(to: emailextConfig.to,
                     from: emailextConfig.from,
                     body: emailextConfig.body,
                     mimeType: emailextConfig.mimeType,
                     subject: "Jenkins Build [${currentBuild.fullDisplayName}] Regression - [${currentBuild.result}] for application [${applicationName}]")
        } 
        fixed { //Run if the previous build was not successful and the current builds status is "Success"
            emailext(to: emailextConfig.to,
                     from: emailextConfig.from,
                     body: emailextConfig.body,
                     mimeType: emailextConfig.mimeType,
                     subject: "Jenkins Build [${currentBuild.fullDisplayName}] Success - [${currentBuild.result}] for application [${applicationName}]")
        }
    }
}

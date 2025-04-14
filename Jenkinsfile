// Project's POM file
def applicationName = 'JavaTemplates'
def projectPom = './pom.xml'

// Email extension plugin base parameters
def emailextConfig = [
    to: 'pierre.lupien@hrsdc-rhdcc.gc.ca,ahmad.shahid@hrsdc-rhdcc.gc.ca', //comma-separated lits of addresses
    from: 'Jenkins-CI <jenkins-ci@jade-build.intra.dev>',
    body: '${SCRIPT, template="groovy-html.template"}', //for details on body: https://wiki.jenkins.io/display/JENKINS/Email-ext+plugin#Email-extplugin-Scriptcontent
    mimeType: 'text/html'    
    ]

pipeline {
    
    agent any; //any: Run on any available agent - agent is same for all stages
               //none: No top-level setting.  Each stage must declare its own agent. Needed for non-blocking input.
    
    options {
        buildDiscarder logRotator(artifactNumToKeepStr: '5', numToKeepStr: '10')
        disableConcurrentBuilds()
        timestamps()
    }
    
    tools {
        maven('maven')
        git('git')
        ant('Ant')
        jdk('OpenJDK11')
    }
   
    parameters {
      string(defaultValue: '', description: 'Deploy version (e.g. 2.0.1) Leave blank to use version form source code.', name: 'DEPLOY_VERSION', trim: true)
    }    
    
    stages {
        stage('Project Artifact Version Check') {
            steps {
                script {
                    def projectModel = readMavenPom(file: projectPom)
                    
                    if (params.DEPLOY_VERSION.isEmpty()) {
                        // No version parameter specified: make sure the one from pom file is Snapshot
                        
                        if (!projectModel.version.endsWith('-SNAPSHOT')) {
                            currentBuild.result = 'ABORTED'
                            error('Building non-SNAPSHOT versions must be explicitly triggerd by specifying the DEPLOY_VERSION parameter.')
                        }
                    }
                    else {
                        // A version parameter was specified, perform some checks
                    
                        if (params.DEPLOY_VERSION.contains(" ")) {
                            currentBuild.result = 'ABORTED'
                            error('DEPLOY_VERSION must not contain spaces.')
                        }
                        
                        if (!params.DEPLOY_VERSION.toUpperCase().endsWith('-SNAPSHOT')) {
                            //RELEASE build: make sure our dependencies are not snapshots
                            def mavenDesc = Artifactory.mavenDescriptor()
                            
                            mavenDesc.pomFile = projectPom
                            if (mavenDesc.hasSnapshots()) {
                                currentBuild.result = 'ABORTED'
                                error('Snapshot(s) detected in dependencies.  This is a release build.  Snapshot dependencies are not allowed.')
                            }
                        }
                    }
                } //of script
            } //of steps
        } //of stage

        stage('Release build confirmation') {
            when {
                beforeInput true
                expression {
                    return (!params.DEPLOY_VERSION.isEmpty()) && (!params.DEPLOY_VERSION.toUpperCase().endsWith('-SNAPSHOT'));
                }
            }
            input {
                message "About to deploy RELEASE version [${params.DEPLOY_VERSION}] to Artifactory, are you sure?"
                ok 'Yes, deploy!'
                submitterParameter 'DEPLOY_SUBMITTER'
            }
            steps {
                script {
                    sh(script: "echo Release build authorized.")
                }
            }
        }
        
        stage('Build and Deploy') {
            steps {
                script {
                    def git = tool('git')
                    def gitCommitId = sh(script: "'${git}' rev-parse HEAD", returnStdout: true).trim()
                    
                    withAnt(ant: 'Ant', jdk: 'JDK11') {
                        withMaven(maven: 'maven', jdk: 'JDK11') {
                            //---[ If an explicit version was specified, override source's versions
                            if (!params.DEPLOY_VERSION.isEmpty()) {
                                sh(script: "ant -buildfile ./builds/build-setprojectversion.xml \"-Dgocwebtemplate.build.version=${params.DEPLOY_VERSION}\"")
                            }

                            //---[ Build/deploy main projects
                            sh(script: "mvn --batch-mode --errors --update-snapshots -Dbuild_number=${BUILD_NUMBER} -Dbuild_git_commitid=${gitCommitId} --file ${projectPom} clean deploy")
                            
                            //---[ Build/deploy archetypes
                            sh(script: "ant -buildfile ./builds/build-archetypes.xml")

                            //---[ If this is a release version, build ZIP file for external clients
                            if ((!params.DEPLOY_VERSION.isEmpty()) && (!params.DEPLOY_VERSION.toUpperCase().endsWith('-SNAPSHOT'))) {
                                sh(script: "ant -buildfile ./builds/build-release.xml")
                            }
                        }
                    }
                }
            }
        }
    }

    post {
        always { //Always run, regardless of build status
            archiveArtifacts(artifacts: "gocwebtemplate-*/**/target/*.?ar", allowEmptyArchive: true, fingerprint: true)
            archiveArtifacts(artifacts: "builds/target/gocwebtemplate-*-${params.DEPLOY_VERSION}.zip", allowEmptyArchive: true, fingerprint: true)
            
            junit(testResults: "gocwebtemplate-*/**/target/surefire-reports/TEST-*.xml", allowEmptyResults: true)
            
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

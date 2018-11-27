# Building the Java Web Templates

## Work flow

Since in Maven the convention is to never re-use a "release" version, a number
of scripts were created to help avoid mistakes between release versions and
snapshot versions.

### Preparing to work on a new version

To start/open a new version (including the creation of the version branch in Git):

- Make sure your local repository is on branch 'master' with no outstanding 
  files or commit.
- Open a Git bash window
- "cd" to the root directory of the Git repository (e.g. cd /c/git/JavaTemplates)
- Run the command: ./builds/create-version.sh


### Making a release build

Once all development/changes have been merged back from the version branch
to 'master', do the following before creating the release build:

- Make sure your local repository is on branch 'master' with no outstanding 
  files or commit and that it is up to date with origin.
- Open a Git bash window
- "cd" to the root directory of the Git repository (e.g. cd /c/git/JavaTemplates)
- Run the command: ./builds/prepare-release.sh

This script will automatically strip the "-SNAPSHOT" from the version and
commit the changes.


## Ant Scripts

### build-archetypes.xml

This Ant script will put together the needed files to create a Maven archetype
from the sample projecs and launch a "mvn deloy" on the generated projects.

Because Maven deployment targets SADE's Artifactory, this script is meant to be
launched from Jenkins (https://jade-build.intra.dev/jenkins/job/ca.gc.sds/).

### build-release.xml

This Ant script will create a ZIP archive from the sample projects meant to be
downloaded by users outside the ESDC network.  Prior to packaging, the sample
projects are modified slightly to include the internal dependencies in a 
Maven repository local to the project (because external users don't have 
access to SADE's Artifactory installation).

This script can be launched locally (typically from 'master' branch after 
finalizing the release by calling prepare-release.sh, see above) 

### build-setprojectversion.xml

This script is used to update the version number in all pom.xml and
configuration files so they all match.  

This script is meant to be launched by the release shell scripts, but can be 
used manually whenever there is a need to change the version.

#!/bin/bash

# This script is used to prepare a branch (typically 'master') for a release build.
# It is meant to be executed from the repository's root directory.
# See at the bottom for exactly what it performs.

# ##### Load functions
source `dirname $0`/_common-functions.sh

BUILD_PROPERTIES_LOCATION="./builds/build.properties"

# ##### A bit of validation...
checkRequiredFiles
checkWDIsGitRepository
checkGitRepositoryIsClean

# ##### Init
set -e  #Stop script on errors
echo 

confirmGitBranchMaster
echo

# ##### Infer the version number from the current version
echo "Reading [${BUILD_PROPERTIES_LOCATION}]..."
PROJECT_VERSION=`grep gocwebtemplate.build.version ${BUILD_PROPERTIES_LOCATION} | cut -d'=' -f2 -s`
PROJECT_RELEASE_VERSION=${PROJECT_VERSION/-SNAPSHOT/}
echo -e "${GREEN}Current project version is [${PROJECT_VERSION}].${NC}"
echo -e "${GREEN}RELEASE project version will be [${PROJECT_RELEASE_VERSION}].${NC}"
echo

# ##### A chance to turn back...
echo -e "${RED}This is what is about to happen:"
echo "  - An Ant script will be launched to change the version of all projects to '${PROJECT_RELEASE_VERSION}'."
echo "  - The changes to the version will be commited."
echo "  - The commit will be pushed to origin."
echo "  - This script, satisfied with a job well done, will exit."
echo "(either that or there will be an error you'll have to troubleshoot.)"
echo
echo -e "This is your last chance to cancel before these steps are executed.${NC}"
echo
read -p "Are you sure you want to continue? (y|n) " userInput
if [ "${userInput}" != 'y' ]; then
    echo "Aborted by user. - Nothing was done."
    exit 2
fi

# ##### Go!
set -x  # Echo executed commands to standard output

ant -Dgocwebtemplate.build.version="${PROJECT_RELEASE_VERSION}" -f ${VERSION_ANTSCRIPT_LOCATION}
git commit -a -m "Preparing release of version ${PROJECT_RELEASE_VERSION}"
git push

set +x # Stop echoing commands
echo -e "${GREEN}Done. You can now perform your release builds.${NC}"


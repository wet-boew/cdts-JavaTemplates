#!/bin/bash

# This script is used to initialize a new version branch of the JavaWebTemplates repository/projects.
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

# ##### Get version value from user (if not already specified)
if [ -z ${PROJECT_VERSION} ]; then
    read -p "Please enter the target version number (e.g. 1.29.0): " PROJECT_VERSION
    if [ -z ${PROJECT_VERSION} ]; then
        echo "No value supplied for project version. - Nothing was done."
	exit 2
    fi
fi
echo

# ##### A chance to turn back...
echo -e "${RED}This is what is about to happen:"
echo "  - A 'git pull' will be performed."
echo "  - A new branch 'release-${PROJECT_VERSION}' will be created."
echo "  - An Ant script will be launched to change the version of all projects to '${PROJECT_VERSION}-SNAPSHOT'."
echo "  - The changes to the version will be commited."
echo "  - The new branch will be pushed to 'origin' and locally set to track new remote branch."
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

git pull
git checkout -b "release-${PROJECT_VERSION}"
ant -Dgocwebtemplate.build.version="${PROJECT_VERSION}-SNAPSHOT" -f ${VERSION_ANTSCRIPT_LOCATION}
git commit -a -m "Preparing version branch for version ${PROJECT_VERSION}-SNAPSHOT"
git push origin "release-${PROJECT_VERSION}"
git branch -u "origin/release-${PROJECT_VERSION}"

set +x # Stop echoing commands
echo -e "${GREEN}Done. You should now be on branch 'b${PROJECT_VERSION}'.${NC}"


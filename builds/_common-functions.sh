#!/bin/bash

# This script is meant to be included in other scripts.
# It will do nothing if executed directly.

# A few colors for the echo command (ref: https://stackoverflow.com/questions/5947742/how-to-change-the-output-color-of-echo-in-linux#5947802)
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' #NO COLOR

VERSION_ANTSCRIPT_LOCATION="./builds/build-setprojectversion.xml"

function checkExecInPath {
    command -v $1
    if [ $? != 0 ]; then
        echo "ERROR: $1 command not found in PATH, aborting."
	exit 1
    fi
}

function checkRequiredFiles {
# ##### Check that we have what we need
    checkExecInPath java
    checkExecInPath ant
    checkExecInPath git
    if ! [ -f ${VERSION_ANTSCRIPT_LOCATION} ]; then
        echo "Ant script [${VERSION_ANTSCRIPT_LOCATION}] not found, aborting."
        exit 1
    fi
    echo "Ant script location: [${VERSION_ANTSCRIPT_LOCATION}]."
}

function checkWDIsGitRepository {
# ##### Check that we are in a Git repository
    if ! [ -d .git ]; then
        echo "Current directory not a Git repository, aborting."
        exit 1
    fi
    git rev-parse --git-dir > /dev/null;
    if [ $? != 0 ]; then
        echo "Current directory not a Git repository, aborting."
        exit 1
    fi
}

function checkGitRepositoryIsClean {
# ##### Check that this Git repository is "clean"
    test -z "$(git status --porcelain)"
    if [ $? != 0 ]; then
        echo -e "${RED}ERROR: This repository is not clean.  Make sure there are no untracked or staged files before continuing.${NC}"
        exit 1
    fi
    test -z "$(git cherry)" #check if there are commits to be pushed
    if [ $? != 0 ]; then 
        echo -e "${RED}ERROR: This repository is not clean.  Make sure all outstanding commits have been pushed before continuing.${NC}"
        exit 1
    fi
}

function confirmGitBranchMaster {
# ##### Check which git branch we're on
    VCS_BRANCH=`git branch | grep \*`
    if [ "${VCS_BRANCH}" != "* master" ]; then
        echo -e "${YELLOW}WARNING: You are NOT currently on branch '* master', you are on branch '${VCS_BRANCH}'.${NC}"
        read -p "Are you sure you want to continue? (y|n) " userInput
        if [ "${userInput}" != 'y' ]; then
            echo "Aborted by user. - Nothing was done."
        exit 2
        fi
    fi
}


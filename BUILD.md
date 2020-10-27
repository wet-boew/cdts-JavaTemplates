# Building the Java Web Templates

## Development

Development work is done directly against the master branch, and should always 
happen with a "-SNAPSHOT" version for all components/config files.  Gitlab pipelines
will automatically build and test the JavaTemplates projects on push/merge. These
automatically triggered pipelines will _not_ deploy to artifactory. 

## Releasing the JavaTemplates

Note this section is intended for developers working on the JavaTemplates who have permission to deploy a release.

### Step 1 - Prepare the Solution

* Make sure all changes that are intended to be released have been merged into the master branch.
* Make sure [Constants.java](https://gccode.ssc-spc.gc.ca/iitb-dgiit/sds/GOCWebTemplates/JavaTemplates/-/blob/master/gocwebtemplate-core/gocwebtemplate-core-base/src/main/java/goc/webtemplate/Constants.java)
  is updated with proper version(s).
* If the release includes a CDTS version change, make sure all `cdn.properties` files have been updated accordingly.
* Update the [CHANGELOG](https://gccode.ssc-spc.gc.ca/iitb-dgiit/sds/GOCWebTemplates/JavaTemplates/-/blob/master/CHANGELOG.md) 
  identifying all _Breaking changes_, _New features and improvements_, and _Fixes / Patches_ form the last release; identify the version number appropriately for [Semantic Versioning](https://semver.org/).

### Step 2 - Deployment

1. Navigate to [CI / CD -> Pipelines](https://gccode.ssc-spc.gc.ca/iitb-dgiit/sds/GOCWebTemplates/JavaTemplates/-/pipelines) for the project.
1. Click _Run Pipeline_
1. Under _Run for_ ensure the `master` branch (or other deployable _protected_ branch) is selected (master should be default)
1. Add a _Variable_ with:
   - The _Key_ being `DEPLOY_VERSION` (Will match the key in the [.gitlab-ci.yml](https://gccode.ssc-spc.gc.ca/iitb-dgiit/sds/GOCWebTemplates/JavaTemplates/-/blob/master/.gitlab-ci.yml)
   - The _Value_ being the **version** you are deploying. This version value will 
     override the version of all pom files for the deployment build. Format must be:
     - For a **SNAPSHOT** version: `x.y.z-SNAPSHOT`
     - For a **RELEASE** version: `x.y.z`
1. Click _Run Pipeline_

The pipeline will run and deploy the JavaTemplates artifact to ESDC Artifactory:
  - [Snapshot deployments repository](https://jade-repos.intra.dev/artifactory/webapp/#/artifacts/browse/tree/General/ca-gc-sds-libs-snapshot-local)
  - [Release deployments repository](https://jade-repos.intra.dev/artifactory/webapp/#/artifacts/browse/tree/General/ca-gc-sds-libs-release-local)

In addition, the **release** deployment will generate ZIP files for external departments. 
These ZIP files will be available in the pipeline `deploy_release` job's page and should be used to update the 
[Installation page](https://gccode.ssc-spc.gc.ca/iitb-dgiit/sds/GOCWebTemplates/JavaTemplates/-/wikis/Documentation/Installation#installation).
  
### Step 3 - Update Wiki

After a release deployment, the [Wiki Installation Page](https://gccode.ssc-spc.gc.ca/iitb-dgiit/sds/GOCWebTemplates/JavaTemplates/-/wikis/Documentation/Installation)
should be updated with the appropriate information (including the new set of ZIP files for external departments).
  

## Ant Scripts

### build-setprojectversion.xml

This script is used to update the version number in all pom.xml _and
configuration files_ so they all match.  

This script is meant to be launched by the release pipeline scripts, but can be 
used manually whenever there is a need to change the version.

### build-archetypes.xml

This Ant script will put together the needed files to create a Maven archetype
from the sample projecs and launch a "mvn deloy" on the generated projects.

Because Maven deployment targets SADE's Artifactory, this script is mainly 
launched from the pipeline.

### build-release.xml

This Ant script will create a ZIP archive from the sample projects meant to be
downloaded by users outside the ESDC network.  Prior to packaging, the sample
projects are modified slightly to include the internal dependencies in a 
Maven repository local to the project (because external users don't have 
access to SADE's Artifactory installation).

This script can be launched locally (typically from 'master' branch after 
finalizing the release by calling prepare-release.sh, see above), but is 
mainly used by the build pipeline.  


### Summary for createTag task

* generate tag string (straight-forward)
    * `def tag = "easywar-${project.version}-${timestamp}-${mnemonics}"` 
    * uses `~/devops/utils/Mnemonic` Node app for the Docker-names
* query Artifactory for latest build info
    * this is the risky part re: race-condition
    * use latest build info to get the URL for the artifact
* update CSV file
    * date, tag string, Artifactory URL

### Risk

* Alice builds `version X` and auto-deploys to DEV
* Artifactory `latest` == `version X`
* Alice goes to lunch
* Bob builds `version Y` and auto-deploys to DEV
* Artifactory `latest` == `version Y`
* Alice runs `gradle createTag`, unaware of Bob. 
    * tag is created pointing to artifact for `version Y` when she wanted `version X` 

* easy-but-weak mitigation options:
    * `createTag` logs the Git commit hash used, which should be verified by developer
    * We could include the short commit hash in the tag itself.
* stronger mitigation options:
    * `createTag` could request user-input and fail if the assumption is invalid. 
    * We could potentially auto-tag each build.

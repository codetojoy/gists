
* handle multi-ENV ?
    * multiple CSV files
    * for now, just use the same repo URL ?
* consider JSON over CSV
* consider the random words
* X - re-establish publishing
* X - alter the meta-data so it contains the Git commit
* X - automate tagging
* X - refactor gradle build
* X - mock deployment via tags

### misc

* tag operation
    * determines Artifactory URL of latest build
        * adds entry to file
        * maps Artifactory URL to presumptive tag 
        * commits mapping file
    * creates tag in GitHub
* file format
    * date,tag, artifactory URL
* deployment
    * select tag
    * look up appropriate URL in mapping
    * use that artifact

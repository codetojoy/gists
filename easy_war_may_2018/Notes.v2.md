
* address the append problem
* consider adding the git commit hash to the CSV file
* consider the git commit hash in the tag itself ?
* unmock the deploy ?

* X - consider a utility that reads the POM for an Artifactory URL
* X - consider a log statement that states the latest Git hash with the Artifactory one
* X - handle multi-ENV ?
    * X - multiple CSV files
    * X - for now, just use the same repo URL ?
* y - consider JSON over CSV
    * CSV offers the easy view/search
* X - consider the random words
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

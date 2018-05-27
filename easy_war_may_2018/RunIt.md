
* updated 26-MAY-2018 

### Build Notes

* assume that Artifactory is installed locally on port 8081
* `gradle clean build publish` will publish the WAR file
    * POM meta-data contains a `properties` node with:
        * build timestamp
        * git commit info 
        * TODO: tag info

### Tag Notes

* `gradle createTag`

### Deploy Notes

* Jenkins job uses Git Parameter plugin, passes ENV var as property to Gradle
* Gradle `mockDeploy` task maps the target tag to the Artifactory URL
* actual deployment should be simple from there

### Useful Commands

* see scripts in `devops`

### Legacy Deploy Notes

* for local Tomcat: 
    * set TOMCAT_HOME in `run.sh`
    * `. ./setvars.sh`
    * './run.sh` to build and deploy 
* for local Jenkins, it is simple to set up as a Free-Form job
    * be sure to use `-p artifactory_jan_2017` for Gradle switch


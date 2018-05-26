
### Build Notes

* updated 26-MAY-2018 
* assume that Artifactory is installed locally on port 8081
* `gradle clean build publish` will publish the WAR file
    * POM meta-data contains a `properties` node with:
        * build timestamp
        * git commit info 
        * TODO: tag info
### Legacy Deploy Notes

* for local Tomcat: 
    * set TOMCAT_HOME in `run.sh`
    * `. ./setvars.sh`
    * './run.sh` to build and deploy 
* for local Jenkins, it is simple to set up as a Free-Form job
    * be sure to use `-p artifactory_jan_2017` for Gradle switch


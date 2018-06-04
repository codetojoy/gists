
### Notes

* Artifactory plugin
    * does not require `publishing` in `build.gradle`
    * Jenkins simply calls `gradle war`
    * however, there is no POM in Artifactory
    * there is build info in the Builds section
* via command-line
    * with `publishing` in `build.gradle`
    * using `gradle clean build publish`
    * puts POM in Artifactory
    * no build info in the Builds section

### Random, June 2018 

* Q: what if we change Jenkins to call `gradle build publish` ?
    * it didn't publish the POM
    * not sure if it is a configuation issue in the Artifactory Plugin ?

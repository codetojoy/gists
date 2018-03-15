
### Usage

* `gradle build`
* cd `build/libs`
* `java -jar embedded_tomcat_mar_2018-all.jar port path appWarFile`, where
    * port example:       `5150`
    * path example:       `ping`
    * appWarFile example: `$ROOT/ping.war` 

### Notes

* this will unpack the war file into `~/myWebapps` and then load it as a webapp
    * this sucks but for the current experiment, it might be OK
    * of course, Spring Boot offers far more elegant approaches


### usage

* `gradle run`

### Java 8 

* OK with Java 8, Gradle 3.5.1, Groovy 2.4.15
* OK with Java 8, Gradle 4.10 , Groovy 2.4.15

### Java 9

* OK with Java 9+181 (Oracle), Gradle 3.5.1, Groovy 2.4.15
    * issues `WARNING: An illegal reflective access operation has occurred`
    * issues `WARNING: All illegal access operations will be denied in a future release`

### Java 10

* tested with Java 10.0.2 (Oracle), Gradle 3.5.1, Groovy 2.4.15
    * Gradle not happy `Could not determine java version from '10.0.2'.` 
* OK with Java 10.0.2 (Oracle) and Gradle 4.10, Groovy 2.4.15
    * issues `WARNING: An illegal reflective access operation has occurred`
    * issues `WARNING: All illegal access operations will be denied in a future release`

### Java 11

* OK with Open JDK 11-ea+26, Gradle 4.10, Groovy 2.4.15
    * issues `WARNING: An illegal reflective access operation has occurred`
    * issues `WARNING: All illegal access operations will be denied in a future release`

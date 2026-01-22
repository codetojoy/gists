
### Goal

A minimal, sandbox project that illustrates the interoperability between Java and Kotlin.

### Phase 1 [COMPLETE] TODOs

* [COMPLETE] TODO: a small program in Java that calls a Kotlin class, `Animal`. An animal should make a sound based on an input parameter (e.g. 1 == 'Meow', 2 == 'Woof', etc).
* [COMPLETE] TODO: a small program in Kotlin that calls a Java class, `Vehicle`. A vehicle should use a fuel based on an input paramter (e.g. 1 == 'Electric', 2 == 'Gasoline', etc).
* [COMPLETE] TODO: each program should have a runner Bash script which do not need to be the same.
* [COMPLETE] TODO: Use Java 21 and Kotlin 2.2.x. Assume these are installed in the Terminal with SDKMan!

### [COMPLETE] Phase 2 TODOs

* [COMPLETE] TODO: Generate `src/locations.txt` with a list of 20 random locations (cities, US state parks, etc).
* [COMPLETE] TODO: `Animal.kt` should read `src/locations.txt`, into a Sequence of strings. `JavaMain.java` should query `Animal.kt` for those locations and print them out.
* [COMPLETE] TODO: Similarly, `Vehicle.java` should read `src/locations.txt` into an ArrayList of strings. `KotlinMain.kt` should query `Vehicle.java` for those locations and print them out. 

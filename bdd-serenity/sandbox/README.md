
* JUL 2021
* derived from BDD in Action, 1st edition
    - the book uses JBehave
    - examples were switched from JBehave to Serenity/Cucumber ???
* tested with:
    - JDK 11
    - Gradle 6.7
* see tasks for Serenity tasks
* it seems to use Cucumber and not JBehave?
    - per dependencies
* tests run JUL 2021
    - `./test.sh`
* report: ~/site/serenity/index.html

### high-level runner 

src/test/java/manning/bddinaction/acceptancetests/AcceptanceTestSuite.java

### feature

src/test/resources/features/itineraries/show_next_departing_trains.feature

### test: BDD step
 
src/test/java/manning/bddinaction/steps/DepartingTrainsStepDefinitions.java

### test: utility 

src/test/java/manning/bddinaction/timetables/LocalTimes.java

### prod code

src/main/java/manning/bddinaction/timetables/CanScheduleServices.java
src/main/java/manning/bddinaction/timetables/InMemoryTimeTable.java
src/main/java/manning/bddinaction/timetables/ScheduledService.java
src/main/java/manning/bddinaction/timetables/TimeTable.java
src/main/java/manning/bddinaction/timetables/UnknownLineException.java
src/main/java/manning/bddinaction/itineraries/ItineraryService.java



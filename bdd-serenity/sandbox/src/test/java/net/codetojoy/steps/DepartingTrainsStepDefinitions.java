package net.codetojoy.steps;

import cucumber.api.java.en.*;
import net.codetojoy.itineraries.*;
import net.codetojoy.timetables.*;

import java.time.*;
import java.util.*;
import static java.util.stream.Collectors.toList;

import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;

public class DepartingTrainsStepDefinitions {

    InMemoryTimeTable timeTable = new InMemoryTimeTable();
    ItineraryService itineraryService = new ItineraryService(timeTable);

    @Given("the (.*) train to (.*) leaves (.*) at (.*)")
    public void theTrainLeavesAt(String line,
                                 String to,
                                 String from,
                                 String departingAt) {
        var departureTimes = localTimesFrom(departingAt);
        timeTable.scheduleService(line, departureTimes, from, to);
    }

    List<LocalTime> proposedDepartures;

    @When("Travis want to travel from (.*) to (.*) at (.*)")
    public void travelBetween(String from, String to, String departingAt) {
        var departureTime = LocalTime.parse(departingAt);
        proposedDepartures = itineraryService.findNextDepartures(departureTime,
                from,
                to);
    }

    @Then("he should be told about the trains at: (.*)")
    public void shouldBeToldAboutTheTrainsAt(String expectedDepartures) {
        var expected = localTimesFrom(expectedDepartures);
        assertThat(proposedDepartures).isEqualTo(expected);
    }

    private List<LocalTime> localTimesFrom(String listOfDepartureTimes) {
        return stream(listOfDepartureTimes.split(","))
                .map(String::trim)
                .map(LocalTime::parse)
                .collect(toList());
    }
}

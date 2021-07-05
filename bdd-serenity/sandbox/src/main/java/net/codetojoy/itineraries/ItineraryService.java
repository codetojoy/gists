package net.codetojoy.itineraries;

import net.codetojoy.timetables.TimeTable;

import java.time.*;
import java.util.*;
import static java.util.stream.Collectors.toList;

public class ItineraryService {
    private TimeTable timeTable;

    public ItineraryService(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public List<LocalTime> findNextDepartures(LocalTime departureTime, String from, String to) {

        var lines = timeTable.findLinesThrough(from, to);

        return lines.stream()
                .flatMap(line -> timeTable.getDepartures(line, from)
                                          .stream())
                .filter(trainTime -> !trainTime.isBefore(departureTime))
                .sorted()
                .limit(2)
                .collect(toList());
    }
}

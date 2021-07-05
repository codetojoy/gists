package net.codetojoy.timetables;

import java.time.*;
import java.util.*;
import static java.util.stream.Collectors.toList;

public class InMemoryTimeTable implements TimeTable, CanScheduleServices {

    private final Map<String, ScheduledService> schedules = new HashMap<>();

    @Override
    public void scheduleService(String line,
                                List<LocalTime> departingAt,
                                String from,
                                String to) {
        schedules.put(line, new ScheduledService(from, to, departingAt));

    }

    private Set<String> lineNames() { return  schedules.keySet(); }

    private boolean lineGoesThrough(String line, String from, String to) {
        return schedules.getOrDefault(line, ScheduledService.NO_SERVICE)
                        .goesBetween(from,to);
    }
    @Override
    public List<String> findLinesThrough(String from, String to) {
        return lineNames().stream()
                .filter(line  -> lineGoesThrough(line, from,to))
                .collect(toList());
    }

    @Override
    public List<LocalTime> getDepartures(String lineName, String from) {
        if (!schedules.containsKey(lineName)) {
            throw new UnknownLineException("No line found: " + lineName);
        }
        return schedules.get(lineName).getDepartureTimes();
    }
}

package net.codetojoy.timetables;

import java.time.*;;
import java.util.*;
import java.util.stream.Collectors;

public class LocalTimes {
    public static List<LocalTime> at(String... times) {
        return Arrays.stream(times)
                .map(LocalTime::parse)
                .collect(Collectors.toList());
    }
}

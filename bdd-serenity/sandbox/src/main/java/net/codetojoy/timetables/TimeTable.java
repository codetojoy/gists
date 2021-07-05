package net.codetojoy.timetables;

import java.time.*;
import java.util.*;

public interface TimeTable {
    List<String> findLinesThrough(String departingFrom, String goingTo);
    List<LocalTime> getDepartures(String lineName, String departingFrom);
}

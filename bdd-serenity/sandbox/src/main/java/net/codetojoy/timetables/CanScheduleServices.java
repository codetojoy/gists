package net.codetojoy.timetables;

import java.time.*;
import java.util.*;

interface CanScheduleServices {
    void scheduleService(String line,
                         List<LocalTime> departingAt,
                         String departure,
                         String destination);

}

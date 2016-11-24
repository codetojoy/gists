
package net.codetojoy;

import org.joda.time.*;
import org.joda.time.format.*;

public class TimeUtils {
    private static final String FORMAT = "YYYY-MM-dd HH:mm";
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT);

    public String getDefaultTimeZoneID() {
        return DateTimeZone.getDefault().getID();
    }

    public boolean contains(String t, String start, String end, String zoneID) {
        boolean result = false;

        DateTimeZone zone = DateTimeZone.forID(zoneID);
        DateTime startDateTime = formatter.parseDateTime(start).withZone(zone);
        DateTime endDateTime = formatter.parseDateTime(end).withZone(zone);
        DateTime tDateTime = formatter.parseDateTime(t).withZone(zone);

        Interval interval = new Interval(startDateTime, endDateTime);
        result = interval.contains(tDateTime);

        return result;
    }
}

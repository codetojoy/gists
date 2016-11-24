
package net.codetojoy;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

import org.joda.time.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.CoreMatchers.is;

@RunWith(value = Parameterized.class)
public class TimeUtilsTestCase {
    private TimeUtils timeUtils = new TimeUtils();
    private String zoneID = timeUtils.getDefaultTimeZoneID();

    private static final String DAY_A = "2016-11-01 ";
    private static final String DAY_B = "2016-11-02 ";
    private static final String START = "08:00";
    private static final String END = "23:59";

    private String t;
    private String start;
    private String end;
    private boolean expected;

    public TimeUtilsTestCase(String t, String start, String end, boolean expected) {
        this.t = t;
        this.start = start;
        this.end = end;
        this.expected = expected;
    }

    @Parameters(name = "test #{index}: IS {0} IN RANGE ({1}, {2}) ? {3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // { t, start, end, expected } 
                // expected == is t in (start, end) interval ?
                {DAY_A + "12:00", DAY_A + START, DAY_A + END, true},
                {DAY_A + "07:00", DAY_A + START, DAY_A + END, false},
                {DAY_B + "00:00", DAY_A + START, DAY_A + END, false},
                {DAY_A + "23:30", DAY_A + "23:00", DAY_B + "04:00", true},
                {DAY_A + "22:00", DAY_A + "23:00", DAY_B + "04:00", false},
                {DAY_B + "04:01", DAY_A + "23:00", DAY_B + "04:00", false}
        });
    }

    @Test
    public void testContains() {
        // test
        boolean result = timeUtils.contains(t, start, end, zoneID);

        assertThat(result, is(expected));
    }
}

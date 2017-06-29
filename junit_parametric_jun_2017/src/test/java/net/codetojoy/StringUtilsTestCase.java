
package net.codetojoy;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.CoreMatchers.is;

@RunWith(value = Parameterized.class)
public class StringUtilsTestCase {
    private String s;
    private boolean expected;
    private String note;
    private String regex; 

    public StringUtilsTestCase(String s, boolean expected, String note, String regex) {
        this.s = s;
        this.expected = expected;
        this.note = note;
        this.regex = regex;
    }

    @Parameters(name = "#{index} ____ {0} ____ {1} ____ {2} {3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
{"d8bebfbc-aea2-4c13-9122-264217042fa3", true, "basic", "____ " + StringUtils.UUID_REGEX},
{"d8bebfbc-aea2-4c13-9122-264217042fa3", true, "group3[0] is 4", ""},
{"d8bebfbc-aea2-6c13-9122-264217042fa3", false, "group3[0] is  4", ""},
{"d8bebfbc-aea2-4c13-8122-264217042fa3", true, "group4[0] in 8,9,A,B", ""},
{"d8bebfbc-aea2-4c13-2122-264217042fa3", false, "group4[0] in 8,9,A,B", ""},
                /*
                {DAY_A + "12:00", DAY_A + START, DAY_A + END, true},
                {DAY_A + "07:00", DAY_A + START, DAY_A + END, false},
                {DAY_B + "00:00", DAY_A + START, DAY_A + END, false},
                {DAY_A + "23:30", DAY_A + "23:00", DAY_B + "04:00", true},
                {DAY_A + "22:00", DAY_A + "23:00", DAY_B + "04:00", false},
                {DAY_B + "04:01", DAY_A + "23:00", DAY_B + "04:00", false}
                */
        });
    }

    @Test
    public void testIsValidUUID() {
        // test
        boolean result = StringUtils.isUUID(s);

        assertThat(result, is(expected));
    }
}

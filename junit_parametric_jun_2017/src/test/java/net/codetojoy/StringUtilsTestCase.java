
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
    private String expected;
    private String note;
    private static final String Y = "Y";
    private static final String N = "N";

    public StringUtilsTestCase(String s, String expected, String note) {
        this.s = s;
        this.expected = expected;
        this.note = note;
    }

    @Parameters(name = "#{index} ____ {0} ____ {1} ____ {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"d8bebfbc-aea2-4c13-9122-264217042fa3", Y, "basic",},
            {"d8bebfbc-aea2-4c13-9122-264217042fa3", Y, "group3[0] is 4",},
            {"d8bebfbc-aea2-6c13-9122-264217042fa3", N, "group3[0] is 4 !",},
            {"d8bebfbc-aea2-4c13-8122-264217042fa3", Y, "group4[0] in {8,9,A,B}",},
            {"d8bebfbc-aea2-4c13-2122-264217042fa3", N, "group4[0] in {8,9,A,B} !",},
        });
    }

    @Test
    public void testIsUUID() {
        boolean expectedResult = (expected.equals(Y));

        // test
        boolean result = StringUtils.isUUID(s);

        assertThat(result, is(expectedResult));
    }
}

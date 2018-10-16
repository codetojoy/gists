
package net.codetojoy;

import org.junit.*;
import static org.junit.Assert.*;

public class UtilsTest {
    private Utils utils = new Utils();

    @Test
    public void testCanary() {
        // test
        assertEquals(4, 2+2);
    }

    @Test
    public void testIsNumeric_Null() {
        // test
        boolean result = utils.isNumeric(null);

        assertFalse(result);
    }

    @Test
    public void testIsNumeric_Empty() {
        // test
        boolean result = utils.isNumeric("");

        assertFalse(result);
    }

    @Test
    public void testIsNumeric_Green() {
        // test
        boolean result = utils.isNumeric("5150");

        assertTrue(result);
    }
}

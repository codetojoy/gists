
package net.codetojoy.utils;

import org.junit.*;
import static org.junit.Assert.*;

public class UtilsTestCase {
    private Utils utils = new Utils();

    @Test
    public void testSafeLength_Basic() {
        String s = "hello";

        // test 
        int result = utils.safeLength(s);
    
        assertEquals(5, result);
    }

    @Test
    public void testSafeLength_Null() {
        String s = null;

        // test 
        int result = utils.safeLength(s);
    
        assertEquals(0, result);
    }

    @Test
    public void testSafeLength_Empty() {
        String s = "";

        // test 
        int result = utils.safeLength(s);
    
        assertEquals(0, result);
    }
}

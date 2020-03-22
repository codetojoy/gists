
package net.codetojoy.example;

import net.codetojoy.util.Util;

import org.junit.*;
import static org.junit.Assert.*;

public class ExampleTestCase {
    private Util util = new Util();

    @Test
    public void testBar() {
        String s = "5150";

        // test
        String result = util.protectedBar(s);

        assertEquals("bar :: 5150", result); 
    }
}

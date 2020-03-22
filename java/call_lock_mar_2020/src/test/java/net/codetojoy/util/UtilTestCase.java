
package net.codetojoy.util;

import org.junit.*;
import static org.junit.Assert.*;

public class UtilTestCase {
    private Util util = new Util();

    @Test
    public void testFoo() {
        String s = "5150";

        // test
        String result = util.foo(s);

        assertEquals("foo :: 5150", result); 
    }

    @Test
    public void testBar() {
        String s = "5150";

        // test
        String result = util.protectedBar(s);

        assertEquals("bar :: 5150", result); 
    }
}

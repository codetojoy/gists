
package net.codetojoy.example2;

import net.codetojoy.util.Util;

import org.junit.*;
import static org.junit.Assert.*;

public class Example2TestCase {
    private Util util = new Util();

    @Test(expected=IllegalStateException.class)
    public void testBar() {
        String s = "5150";

        // test
        String result = util.protectedBar(s);
    }
}

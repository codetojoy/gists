
package net.codetojoy;

import org.junit.*;
import static org.junit.Assert.*;

public class StringTestCase {
    @Test
    public void testSame() {
        String s = "\t abc \n";
        
        assertEquals("abc", s.trim());
        assertEquals("abc", s.strip());
    }

    @Test
    public void testDifferent() {
        Character c = '\u2000';
        String s = c + "abc" + c;

        assertTrue(Character.isWhitespace(c));
        assertEquals(s, s.trim());
        assertEquals("abc", s.strip());
    }
}

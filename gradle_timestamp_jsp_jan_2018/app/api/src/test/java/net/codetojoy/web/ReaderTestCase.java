
package net.codetojoy.web;

import java.util.Properties;

import org.junit.*;
import static org.junit.Assert.*;

public class ReaderTestCase {
    private final Reader reader = new Reader();

    @Test
    public void testCanary() {
        assertEquals(4, 2+2);
    }

    @Test
    public void testRead_TimestampNotFound() {
        Properties properties = new Properties();

        // test
        String result = reader.read(properties);

        assertEquals(Reader.STATUS_TIMESTAMP_NOT_FOUND, result);
    }

    @Test
    public void testRead_TimestampFound() {
        Properties properties = new Properties();
        properties.setProperty(Reader.BUILD_TIMESTAMP, "5150");

        // test
        String result = reader.read(properties);

        assertEquals("5150", result);
    }

    @Test
    public void testRead_PropertyFileNotFound() {
        // test
        String result = reader.read("bogus.properties");

        assertEquals(Reader.STATUS_ERROR, result);
    }
}

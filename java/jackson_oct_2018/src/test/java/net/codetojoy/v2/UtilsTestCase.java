
package net.codetojoy.v2;

import java.io.*;
import java.nio.charset.StandardCharsets;

import org.junit.*;
import static org.junit.Assert.*;

public class UtilsTestCase {
    @Test
    public void testBuildEmployee_String() throws Exception {
        StringBuilder builder = new StringBuilder();

        builder.append("{");
        builder.append(" \"name\" : \"Mozart\", ");
        builder.append(" \"address\" : \"Salzburg\", ");
        builder.append(" \"id\" : 6160, ");
        builder.append(" \"contacts\" : [\"x\", \"y\", \"z\"], ");
        builder.append(" \"reports\" : [");
        builder.append(" { \"name\" : \"report1\", \"id\" : 7170}, ");
        builder.append(" { \"name\" : \"report2\", \"id\" : 8180}, ");
        builder.append(" { \"name\" : \"report3\", \"id\" : 9190} ");
        builder.append("] ");
        builder.append("}");

        String jsonStr = builder.toString();

        // test
        Employee result = new Utils().buildEmployee(jsonStr);

        assertEquals("Mozart", result.getName());
        assertEquals("Salzburg", result.getAddress());
        assertEquals((Integer) 6160, result.getId());

        assertEquals(3, result.getContacts().size());
        assertEquals("x", result.getContacts().get(0));

        assertEquals(3, result.getReports().size());
        assertEquals((Integer) 7170, result.getReports().get(0).getId());
    }

    @Test
    public void testBuildEmployee_InputStream() throws Exception {
        StringBuilder builder = new StringBuilder();

        builder.append("{");
        builder.append(" \"name\" : \"Mozart\", ");
        builder.append(" \"address\" : \"Salzburg\", ");
        builder.append(" \"id\" : 6160, ");
        builder.append(" \"contacts\" : [\"x\", \"y\", \"z\"], ");
        builder.append(" \"reports\" : [");
        builder.append(" { \"name\" : \"report1\", \"id\" : 7170}, ");
        builder.append(" { \"name\" : \"report2\", \"id\" : 8180}, ");
        builder.append(" { \"name\" : \"report3\", \"id\" : 9190} ");
        builder.append("] ");
        builder.append("} ");

        String jsonStr = builder.toString();
        InputStream inputStream = new ByteArrayInputStream(jsonStr.getBytes(StandardCharsets.UTF_8));

        // test
        Employee result = new Utils().buildEmployee(inputStream);

        assertEquals("Mozart", result.getName());
        assertEquals("Salzburg", result.getAddress());
        assertEquals((Integer) 6160, result.getId());

        assertEquals(3, result.getContacts().size());
        assertEquals("x", result.getContacts().get(0));

        assertEquals(3, result.getReports().size());
        assertEquals((Integer) 7170, result.getReports().get(0).getId());
    }
}

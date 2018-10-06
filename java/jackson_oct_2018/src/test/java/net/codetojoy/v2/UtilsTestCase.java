package net.codetojoy.v2;

import java.io.*;
import java.nio.charset.StandardCharsets;

import org.junit.*;
import static org.junit.Assert.*;

public class UtilsTestCase {
    protected String buildEmployeeA() {
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

        return builder.toString();
    }

    protected String buildEmployeeB() {
        StringBuilder builder = new StringBuilder();

        builder.append("{");
        builder.append(" \"name\" : \"Mozart\", ");
        builder.append(" \"address\" : \"Salzburg\", ");
        builder.append(" \"id\" : 6160, ");
        builder.append(" \"contacts\" : [\"x\", \"y\", \"z\"], ");
        builder.append(" \"reports\" : [");
        builder.append(" { \"name\" : \"report1\", \"id\" : 7170}, ");
        builder.append(" { \"name\" : \"report2\", \"id\" : 8180}, ");
        builder.append(" { \"name\" : \"report3\", \"id\" : 9190}  ");
        builder.append("], ");
        builder.append(" \"fields\" : [");
        builder.append(" {\"key\" : \"f1\", \"value\" : \"v1\", \"value_fr\": \"v1_fr\"},  ");
        builder.append(" {");
        builder.append("   \"key\" : \"f2\", \"value\" : \"v2\", \"value_fr\": \"v2_fr\", ");
        builder.append("   \"subFields\":[");
        builder.append("       {\"key\" : \"f2.1\", \"value\" : \"v2.1\", \"value_fr\": \"v2.1_fr\"} ");
        builder.append("   ]");
        builder.append(" }");
        builder.append("] ");
        builder.append("}");

        return builder.toString();
    }

    @Test
    public void testBuildEmployee_caseB_String() throws Exception {
        String jsonStr = buildEmployeeB();

        // test
        Employee result = new Utils().buildEmployee(jsonStr);

        assertEquals("Mozart", result.getName());
        assertEquals("Salzburg", result.getAddress());
        assertEquals((Integer) 6160, result.getId());

        assertEquals(3, result.getContacts().size());
        assertEquals("x", result.getContacts().get(0));

        assertEquals(3, result.getReports().size());
        assertEquals((Integer) 7170, result.getReports().get(0).getId());

        assertEquals(2, result.getFields().size());

        Field f1 = result.getFields().get(0);
        assertEquals("f1", f1.getKey());
        assertEquals("v1", f1.getValue());
        assertEquals("v1_fr", f1.getValueFr());

        Field f2 = result.getFields().get(1);
        assertEquals("f2", f2.getKey());
        assertEquals("v2", f2.getValue());
        assertEquals("v2_fr", f2.getValueFr());

        assertEquals(1, result.getFields().get(1).getSubFields().size());
        Field f21 = result.getFields().get(1).getSubFields().get(0);
        assertEquals("f2.1", f21.getKey());
        assertEquals("v2.1", f21.getValue());
        assertEquals("v2.1_fr", f21.getValueFr());
    }

    @Test
    public void testBuildEmployee_caseA_String() throws Exception {
        String jsonStr = buildEmployeeA();

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
    public void testBuildEmployee_caseA_InputStream() throws Exception {
        String jsonStr = buildEmployeeA();

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

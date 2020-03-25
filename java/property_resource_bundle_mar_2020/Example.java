
import java.util.*;
import java.io.*;

public class Example {
    PropertyResourceBundle buildBundle() throws Exception {
        StringBuilder buffer = new StringBuilder();
        buffer.append("net.codetojoy.foo=4140\n");
        buffer.append("net.codetojoy.test=5150\n");
        buffer.append("net.codetojoy.bar=6160\n");
        String bundleStr = buffer.toString();
        InputStream targetStream = new ByteArrayInputStream(bundleStr.getBytes());
        PropertyResourceBundle bundle = new PropertyResourceBundle(targetStream);
        return bundle;
    }

    void go() throws Exception {
        PropertyResourceBundle bundle = buildBundle();
        String value = bundle.getString("net.codetojoy.test");
        System.out.println("TRACER value: " + value);
    }

    public static void main(String[] args) throws Exception {
        Example example = new Example();
        example.go();
        System.out.println("TRACER Ready");
    }
}

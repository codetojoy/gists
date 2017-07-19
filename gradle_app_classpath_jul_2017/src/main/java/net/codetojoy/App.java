
package net.codetojoy;

import java.io.*;
import java.util.*;

public class App {
    public Properties getProperties(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(App.class.getClassLoader().getResourceAsStream(path));
        return properties;
    }

    public void testFoo() {
        try {
            Properties fooProps = getProperties("resources/foo.properties");
            String value = fooProps.getProperty("foo");
            System.out.println("TRACER value for 'foo' : " + value);
        } catch (Exception ex) {
            System.err.println("ERROR: caught ex : " + ex.getMessage());
        }
    }

    public void testBar() {
        try {
            Properties barProps = getProperties("root/resources/bar.properties");
            String value = barProps.getProperty("bar");
            System.out.println("TRACER value for 'bar' : " + value);
        } catch (Exception ex) {
            System.err.println("ERROR: caught ex : " + ex.getMessage());
        }
    }

    public static void main(String... args) {
        App app = new App();
        app.testFoo();
        app.testBar();
        System.out.println("Ready.");
    }
}

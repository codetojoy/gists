
package net.codetojoy.web;

import java.io.*;
import java.util.*;

public class PropertiesUtils {
    public Properties attemptRead(String filename) throws Exception {
        Properties props = new Properties();

        props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename));

        return props;
    }
}


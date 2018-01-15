
package net.codetojoy.web;

import java.io.*;
import java.util.*;

public class Reader {
    public String read(String filename) {
        String result = "unknown_4";
        Properties props = new Properties();

        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename));
        } catch(Exception ex) {
            result = "exception: " + ex.getMessage();
        }

        if (props != null) {
            result = "from props: " + props.getProperty("BUILD_TIMESTAMP");
        } else {
            result = "error: null props";
        }
    
        return result;
    }
}


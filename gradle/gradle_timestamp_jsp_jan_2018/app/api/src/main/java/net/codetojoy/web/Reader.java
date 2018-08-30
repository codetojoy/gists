
package net.codetojoy.web;

import java.io.*;
import java.util.*;

public class Reader {
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_TIMESTAMP_NOT_FOUND = "not found";
    
    protected static final String BUILD_TIMESTAMP = "BUILD_TIMESTAMP";

    protected String read(Properties properties) {
        String result = STATUS_TIMESTAMP_NOT_FOUND;

        String tmpResult = properties.getProperty(BUILD_TIMESTAMP);

        if (tmpResult != null) { result = tmpResult; }

        return result;
    }

    public String read(String filename) {
        String result = STATUS_ERROR;

        try {
            Properties properties = new PropertiesUtils().attemptRead(filename);
            result = read(properties);
        } catch(Exception ex) {
            // no-op
        }

        return result;
    }
}


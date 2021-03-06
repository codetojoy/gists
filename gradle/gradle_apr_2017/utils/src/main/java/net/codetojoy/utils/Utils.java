
package net.codetojoy.utils;

// build tickle 1

import org.apache.commons.lang3.StringUtils;

import java.io.*;

// This class has some basic fields / methods so that the published jar can be tested.
// The code itself is not interesting. 

public class Utils {
    public void bar() {}
    public void foo() {}

    public int safeLength(String s) {
        int result = 0;
    
        if (! StringUtils.isEmpty(s)) {
            result = s.length();
        }

        return result;
    }

    public String readBuffer(BufferedReader br) {
        String result = "";

        try {
            System.err.println("TRACER UTILS tickle 1");
            StringBuilder buffer = new StringBuilder();
            String currentLine = br.readLine();

            while (currentLine != null) {
                buffer.append(currentLine);
                currentLine = br.readLine();
            }

            result = buffer.toString();
        } catch(Exception ex) {
            System.err.println("caught exception: " + ex.getMessage());
        }        

        return result;
    }
}


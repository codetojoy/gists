
package net.codetojoy;

import java.util.*;

public class Util {
    public String getMessage() {
        String dateStr = new Date().toString();
        String message = dateStr + " Hello from Util.java";
        return message;
    }
}

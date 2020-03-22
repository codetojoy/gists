
package net.codetojoy.util;

import java.util.regex.*;

public class CallLock {
    private static final Pattern PACKAGE_REGEX = Pattern.compile("(.*)\\..*"); 
    private static final String THIS_PACKAGE = "net.codetojoy.util";

    // 0 is Thread
    // 1 is CallLock
    // 2 is the class being locked
    // 3 is the caller 
    private static final int CALLER_INDEX = 3;

    protected void logElements(StackTraceElement[] elements) {
        for (StackTraceElement element : elements) {
            System.out.println("TRACER e: " + element.getClassName());
        }
    }

    public void lock(String targetPackage) {
        boolean isOk = false;

        try {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            logElements(elements);
            StackTraceElement callerElement = elements[CALLER_INDEX]; 
            String callerClassName = callerElement.getClassName();
            System.out.println("TRACER callerClassName: " + callerClassName);
            Matcher matcher = PACKAGE_REGEX.matcher(callerClassName);
            
            if (matcher.matches()) {
                String callerPackageName = matcher.group(1);
                System.out.println("TRACER callerPackageName: " + callerPackageName);
                isOk = targetPackage.equals(callerPackageName) || THIS_PACKAGE.equals(callerPackageName);
            }
        } catch (Exception ex) { 
            System.err.println("caught exception: " + ex.getMessage());
        }
        
        if (! isOk) {
            throw new IllegalStateException("illegal access");
        }
    }
}


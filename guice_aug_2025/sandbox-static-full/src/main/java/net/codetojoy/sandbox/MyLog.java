
package net.codetojoy.sandbox;

public class MyLog {
    public static String buildLog(String message) {
        return buildLog(message, true);
    }

    public static String buildLog(String message, boolean useTracer) {
        String prefix = useTracer ? "TRACER" : "";
        return prefix + " [" + Thread.currentThread().getName() + "] : " + message;
    }
}


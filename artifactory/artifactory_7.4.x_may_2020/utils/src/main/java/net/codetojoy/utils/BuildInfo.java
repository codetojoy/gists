
package net.codetojoy.utils;

public class BuildInfo {
    private static final String artifact = "utils";
    private static final String version = "0.9.8-SNAPSHOT";
    private static final String buildTimestamp = "2020-May-17 16:13:00 PM";

    public String toString() {
        StringBuilder buffer = new StringBuilder();

        buffer.append("artifact: " + artifact + "\n");
        buffer.append("version: " + version + "\n");
        buffer.append("build timestamp: " + buildTimestamp + "\n");

        return buffer.toString();
    }
}

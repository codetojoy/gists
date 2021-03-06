
package net.codetojoy.component;

public class BuildInfo {
    private static final String artifact = "web-component";
    private static final String version = "0.9.8-SNAPSHOT";
    private static final String buildTimestamp = "2017-Jan-26 21:18:37 PM";

    public String toString() {
        StringBuilder buffer = new StringBuilder();

        String utilsBuildInfo = new net.codetojoy.utils.BuildInfo().toString();
        buffer.append(utilsBuildInfo);
        buffer.append("\nartifact: " + artifact + "\n");
        buffer.append("version: " + version + "\n");
        buffer.append("build timestamp: " + buildTimestamp + "\n");

        return buffer.toString();
    }
}

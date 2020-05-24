
package net.codetojoy.component;

public class BuildInfo {
    private static final String artifact = "web-component";
    private static final String version = "1.0.0-SNAPSHOT";
    private static final String buildTimestamp = "2020-May-24 17:58:57 PM";

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

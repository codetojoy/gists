
package net.codetojoy.web;

public class BuildInfo {
    private static final String artifact = "easyweb";
    private static final String version = "0.9.8-SNAPSHOT";
    private static final String buildTimestamp = "2020-May-17 16:47:16 PM";

    public String toString() {
        StringBuilder buffer = new StringBuilder();

        String componentBuildInfo = new net.codetojoy.component.BuildInfo().toString();
        buffer.append(componentBuildInfo);
        buffer.append("\nartifact: " + artifact + "\n");
        buffer.append("version: " + version + "\n");
        buffer.append("build timestamp: " + buildTimestamp + "\n");

        return buffer.toString();
    }
}

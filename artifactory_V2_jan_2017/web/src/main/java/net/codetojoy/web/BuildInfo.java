
package net.codetojoy.web;

public class BuildInfo {
    private static final String artifact = "easyweb";
    private static final String version = "0.9.8-SNAPSHOT";
    private static final String buildTimestamp = "2017-Jan-26 21:20:01 PM";

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

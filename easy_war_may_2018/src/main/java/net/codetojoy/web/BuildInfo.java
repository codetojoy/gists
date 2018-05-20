
package net.codetojoy.web;

public class BuildInfo {
    private static final String artifact = "easytoo";
    private static final String version = "1.0.0-SNAPSHOT";
    private static final String buildTimestamp = "2018-May-20 11:02:47 AM";
    private static final String gitCommitInfo = "commit 3473d729145c78fd66980e37243cf04e8efbde50||Author: Michael Easter <codetojoy@gmail.com>||Date:   Sat May 19 21:29:50 2018 -0300||";

    public String toString() {
        StringBuilder buffer = new StringBuilder();

        buffer.append("\nartifact: " + artifact + "\n");
        buffer.append("version: " + version + "\n");
        buffer.append("build timestamp: " + buildTimestamp + "\n");
        buffer.append("git commit: " + gitCommitInfo + "\n");

        return buffer.toString();
    }
}

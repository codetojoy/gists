
package net.codetojoy;

import java.io.File;

public class PathInfo {
    private final String appPath;
    private final String sharedPath;

    public PathInfo(String appPath, String sharedPath) {
        this.appPath = appPath;
        this.sharedPath = sharedPath;
    }

    public String getAppRootPath() {
        String result = appPath + File.separator + "root";
        return result;
    }

    public String getSharedRootPath() {
        String result = sharedPath + File.separator + "root";
        return result;
    }

    public String getAppPath() {
        return appPath;
    }

    public String getSharedPath() {
        return sharedPath;
    }
}

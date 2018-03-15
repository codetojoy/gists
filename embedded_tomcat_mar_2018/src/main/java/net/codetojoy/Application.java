
package net.codetojoy;

import org.apache.catalina.startup.Tomcat;
import java.io.File;
import java.io.IOException;

public class Application {
    private int port = -1;
    private String appPath = "";
    private String appWar = "";

    public void readArgs(String[] args) {
        if (args.length >= 3) {
            port = Integer.parseInt(args[0]);
            appPath = args[1];
            appWar = args[2];
        } else {
            throw new IllegalStateException("expected args: port path war");
        }
    }

    public void pseudoDeploy(Tomcat tomcat) throws Exception { 
        String destDir = System.getProperty("user.dir") + File.separator + "myWebapps";
        new ZipExtractor().unzip(appWar, destDir);
        tomcat.addWebapp(null, "/" + appPath, destDir);
    }

    public void run() throws Exception {
        String appBase = ".";
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(createTempDir());
        tomcat.setPort(port);
        tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp("", appBase);

        pseudoDeploy(tomcat);
        /*
        try {
            File appDir = new File("my_webapp");
            tomcat.addWebapp(null, "/ping", appDir.getAbsolutePath());
        } catch (Exception ex) {
        }
        */

        tomcat.start();
        tomcat.getServer().await();
    }

    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.readArgs(args);
        app.run();
    }

    private String createTempDir() {
        try {
            File tempDir = File.createTempFile("tomcat.", "." + port);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir.getAbsolutePath();
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"),
                    ex
            );
        }
    }
} 

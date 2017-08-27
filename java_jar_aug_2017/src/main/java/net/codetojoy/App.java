
package net.codetojoy;

import java.io.File;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("TRACER: hello");
            File file = new File("unpack.sh");
            System.out.println("TRACER: unpack.sh : " + file.exists()); 

            String commandLine = "/bin/bash -c ./unpack.sh";
            String[] envp = null;
            File workDir = new File(".");
            Runtime.getRuntime().exec(commandLine, envp, workDir);

            System.out.println("Ready.");
        } catch(Exception ex) {
            System.err.println("Internal Error. ex: " + ex.getMessage());
        }
    }
}


package net.codetojoy;

import java.io.File;

import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import net.lingala.zip4j.core.ZipFile;  

public class JarExtractor {
    public static void unzip(String targetZipFilePath, String destinationFolderPath) {
        try {
            ZipFile zipFile = new ZipFile(targetZipFilePath);
            zipFile.extractAll(destinationFolderPath);
        } catch (Exception e) {
            System.err.println("TRACER caught exception : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("TRACER ready");
        if (args.length < 2) throw new IllegalStateException("check args");

        String src = args[0];
        String destDir = args[1];
        unzip(src, destDir);
    }
}


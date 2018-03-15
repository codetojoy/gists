
package net.codetojoy;

import java.io.File;

import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import net.lingala.zip4j.core.ZipFile;  

public class ZipExtractor {
    public void unzip(String targetZipFilePath, String destinationFolderPath) {
        try {
            ZipFile zipFile = new ZipFile(targetZipFilePath);
            zipFile.extractAll(destinationFolderPath);
        } catch (Exception e) {
            System.err.println("TRACER caught exception : " + e.getMessage());
        }
    }
}

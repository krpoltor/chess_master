package com.capgemini.utils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Utils to read and access files.
 *
 */
public class FileUtils {

    /**
     * Reads file to string.
     *
     * @param file the file
     * @return the string
     */
    public static String readFileToString(File file) {
        return new String(readFileToBytes(file));
    }

    /**
     * Reads file to byte array.
     *
     * @param file the file
     * @return the byte array
     */
    public static byte[] readFileToBytes(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets file from classpath.
     *
     * @param classpath the classpath
     * @return the file
     */
    public static File getFileFromClasspath(String classpath) {
        File file = findFileInClasspath(classpath);
        if (file == null) {
            throw new RuntimeException("File " + classpath + " was not found.");
        }
        return file;
    }

    /**
     * Looks for file in classpath.
     *
     * @param classpath the classpath
     * @return the file
     */
    public static File findFileInClasspath(String classpath) {
        File file = null;
        try {
            Resource resource = new DefaultResourceLoader()
                    .getResource(classpath);
            if (resource.exists()) {
                file = resource.getFile();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}

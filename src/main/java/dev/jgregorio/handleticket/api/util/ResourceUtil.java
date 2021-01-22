package dev.jgregorio.handleticket.api.util;

public class ResourceUtil {

    public static String getRelativePath(Class clazz, String filename) {
        return clazz.getResource(filename).getPath();
    }
}
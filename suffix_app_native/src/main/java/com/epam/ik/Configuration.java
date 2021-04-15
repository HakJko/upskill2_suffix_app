package com.epam.ik;

import java.util.Arrays;

public class Configuration {
    private String suffix;
    private String[] fileNames;

    public Configuration() {
    }

    public String getSuffix() {
        return suffix;
    }

    public String[] getFileNames() {
        return fileNames;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------------------");
        sb.append("Configuration: ");
        sb.append("suffix = " + suffix + "\n");
        sb.append("fileNames = " + Arrays.toString(fileNames));
        sb.append("-------------------------------------");
        return sb.toString();
    }
}

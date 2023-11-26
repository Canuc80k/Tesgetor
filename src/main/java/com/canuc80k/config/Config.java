package com.canuc80k.config;

import java.io.File;
import java.util.List;

import com.canuc80k.launcher.GlobalResource;

public class Config {
    public static File inputGeneratorFile;
    public static File outputGeneratorFile;
    public static File testcaseFolder;

    public Config() {
        locateConfigFiles();
    }

    public synchronized void locateConfigFiles() {
        List<String> configData = GlobalResource.getConfigData();
        inputGeneratorFile = new File(configData.get(0));
        outputGeneratorFile = new File(configData.get(1));
        testcaseFolder = new File(configData.get(2));
    }
}

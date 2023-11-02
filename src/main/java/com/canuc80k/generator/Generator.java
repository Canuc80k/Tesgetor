package com.canuc80k.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;

import com.canuc80k.filetool.FileTool;
import com.canuc80k.launcher.GlobalResource;
import com.canuc80k.testcase.TestcaseFileNameType;

public abstract class Generator {
    protected File inputGeneratorFile;
    protected File outputGeneratorFile;
    protected File testcaseFolder;

    protected ExecutorService threadPool;

    protected String errorInformation;

    public Generator() {
        locateConfigFiles();
    }

    public synchronized void locateConfigFiles() {
        List<String> configData = GlobalResource.getConfigData();
        inputGeneratorFile = new File(configData.get(0));
        outputGeneratorFile = new File(configData.get(1));
        testcaseFolder = new File(configData.get(2));
    }

    public abstract void generate(int beginTestcaseIndex, int endTestcaseIndex, TestcaseFileNameType type, int lastTestcaseFileNameLength, String os, String language, int timeout, Boolean isRunParallel) throws IOException, InterruptedException;

    public synchronized void clear() {
        FileTool.deleteFolder(testcaseFolder, FileTool.KEEP_CURRENT_FOLDER);
        new File(testcaseFolder.getAbsolutePath() + ".zip").delete();
    }

    public synchronized void notifyError(Exception e) {
        errorInformation = e.getClass().getSimpleName();
    }

    public String getErrorInformation() {
        return errorInformation;
    }
}

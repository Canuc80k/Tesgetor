package com.canuc80k.generator;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import com.canuc80k.testcase.TestcaseFileNameType;

public abstract class Generator {
    protected ExecutorService threadPool;

    protected String errorInformation;

    public abstract void generate(int beginTestcaseIndex, int endTestcaseIndex, TestcaseFileNameType type, int lastTestcaseFileNameLength, String os, String language, int timeout, Boolean isRunParallel) throws IOException, InterruptedException;

    public synchronized void notifyError(Exception e) {
        errorInformation = e.getClass().getSimpleName();
    }

    public String getErrorInformation() {
        return errorInformation;
    }
}

package com.canuc80k.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import com.canuc80k.compiler.CPPCompiler;
import com.canuc80k.exception.CompileErrorException;
import com.canuc80k.exception.RuntimeErrorException;
import com.canuc80k.exception.TimeoutException;
import com.canuc80k.filetool.FileTool;
import com.canuc80k.launcher.GlobalResource;
import com.canuc80k.testcase.TestcaseFileNameType;

public class CPPGenerator extends Generator {
    private final File INPUT_GENERATOR_EXE_FILE = new File(GlobalResource.getTempFolder().getAbsolutePath() + "/inputGenerator.exe");
    private final File OUTPUT_GENERATOR_EXE_FILE = new File(GlobalResource.getTempFolder().getAbsolutePath() + "/outputGenerator.exe");

    protected CPPCompiler cppCompiler;

    public CPPGenerator() {
        super();
        cppCompiler = new CPPCompiler();
    }

    @Override
    public void generate(int beginTestcaseIndex, int endTestcaseIndex, TestcaseFileNameType type, int lastTestcaseFileNameLength, String os, String language, int timeout) throws IOException, InterruptedException {
        deleteOldExecuteFiles();
        Boolean compileSuccessfully = compileCplusplusGeneratorFiles(language, os);
        if (!compileSuccessfully) return;
        GlobalResource.getGenerateTestPanel().setTotalTestcase(endTestcaseIndex - beginTestcaseIndex + 1);
        runExcuteFilesToCreateTestcase(beginTestcaseIndex, endTestcaseIndex, type, lastTestcaseFileNameLength, timeout);
    }

    private synchronized void deleteOldExecuteFiles() {
        locateConfigFiles();
        FileTool.deleteFolder(GlobalResource.getTempFolder(), FileTool.KEEP_CURRENT_FOLDER);
    }
    
    private synchronized Boolean compileCplusplusGeneratorFiles(String language, String os) throws IOException, InterruptedException {
        try {
            cppCompiler.compile(inputGeneratorFile, INPUT_GENERATOR_EXE_FILE, language, os);
            cppCompiler.compile(outputGeneratorFile, OUTPUT_GENERATOR_EXE_FILE, language, os);
        } catch (CompileErrorException | TimeoutException | RuntimeErrorException e) {
            JOptionPane.showMessageDialog(
                GlobalResource.getTopDialog(), 
                "Errors occur when compile testcase generator files",
                "Check your testcase generator files",
                JOptionPane.NO_OPTION
            );
            return false;
        }
        return true;
    }

    private synchronized void runExcuteFilesToCreateTestcase(int beginTestcaseIndex, int endTestcaseIndex, TestcaseFileNameType type, int lastTestcaseFileNameLength, int timeout) {
        List<CPPGeneratorTask> tasks = new ArrayList<CPPGeneratorTask>();
        for (int i = beginTestcaseIndex; i <= endTestcaseIndex; i ++) {
            CPPGeneratorTask inputGeneratorThread = new CPPGeneratorTask(
                cppCompiler,
                INPUT_GENERATOR_EXE_FILE,
                OUTPUT_GENERATOR_EXE_FILE,
                testcaseFolder.getAbsolutePath() + "\\" + TestcaseFileNameType.getFileName(type, i, lastTestcaseFileNameLength) + ".INP",
                testcaseFolder.getAbsolutePath() + "\\" + TestcaseFileNameType.getFileName(type, i, lastTestcaseFileNameLength) + ".OUT",
                timeout,
                true
            );
            tasks.add(inputGeneratorThread);
        }

        errorInformation = "";
        final Boolean CHOICE = true; 
        if (!CHOICE) runSequentially(tasks, timeout);
        else runParallel(tasks);
    }

    private synchronized void runSequentially(List<CPPGeneratorTask> tasks, int timeout) {
        for (int i = 0; i < tasks.size(); i ++) {
            Thread thread = new Thread(tasks.get(i));
            try {
                thread.start();
                thread.join();

                System.out.println(errorInformation);
            } catch(InterruptedException e) {
                // GlobalResource.getGenerateTestPanel().stopGenerateTestcase();
            }
        }
    }

    private synchronized void runParallel(List<CPPGeneratorTask> tasks) {
        threadPool = Executors.newCachedThreadPool();
        tasks.forEach((task) -> threadPool.execute(task));
        threadPool.shutdown();
    }
}

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
    public void generate(int beginTestcaseIndex, int endTestcaseIndex, TestcaseFileNameType type, int lastTestcaseFileNameLength, String os, String language, int timeout, Boolean isRunParallel) throws IOException, InterruptedException {
        deleteOldExecuteFiles();
        Boolean compileSuccessfully = compileCplusplusGeneratorFiles(language, os);
        if (!compileSuccessfully) return;

        GlobalResource.getGenerateTestPanel().setTotalTestcase(endTestcaseIndex - beginTestcaseIndex + 1);

        errorInformation = "";
        if (!isRunParallel) 
            runParallelExcuteFilesToCreateTestcase(beginTestcaseIndex, endTestcaseIndex, type, lastTestcaseFileNameLength, timeout);
        else 
            runSequentiallyExcuteFilesToCreateTestcase(beginTestcaseIndex, endTestcaseIndex, type, lastTestcaseFileNameLength, timeout);
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

    private synchronized void runParallelExcuteFilesToCreateTestcase(int beginTestcaseIndex, int endTestcaseIndex, TestcaseFileNameType type, int lastTestcaseFileNameLength, int timeout) {
        List<CPPGeneratorTask> tasks = new ArrayList<CPPGeneratorTask>();
        for (int i = beginTestcaseIndex; i <= endTestcaseIndex; i ++) {
            CPPGeneratorTask inputGeneratorThread = new CPPGeneratorTask(
                cppCompiler,
                INPUT_GENERATOR_EXE_FILE,
                OUTPUT_GENERATOR_EXE_FILE,
                testcaseFolder.getAbsolutePath() + "\\" + TestcaseFileNameType.getFileName(type, i, lastTestcaseFileNameLength) + ".INP",
                testcaseFolder.getAbsolutePath() + "\\" + TestcaseFileNameType.getFileName(type, i, lastTestcaseFileNameLength) + ".OUT",
                timeout,
                false
            );
            tasks.add(inputGeneratorThread);
        }
   
        threadPool = Executors.newCachedThreadPool();
        tasks.forEach((task) -> threadPool.execute(task));
        threadPool.shutdown();
    }

    private synchronized void runSequentiallyExcuteFilesToCreateTestcase(int beginTestcaseIndex, int endTestcaseIndex, TestcaseFileNameType type, int lastTestcaseFileNameLength, int timeout) {
        for (int i = beginTestcaseIndex; i < endTestcaseIndex; i ++) {
            String inputTescaseFilePath = testcaseFolder.getAbsolutePath() + "\\" + TestcaseFileNameType.getFileName(type, i, lastTestcaseFileNameLength) + ".INP";
            String outputTescaseFilePath = testcaseFolder.getAbsolutePath() + "\\" + TestcaseFileNameType.getFileName(type, i, lastTestcaseFileNameLength) + ".OUT";
                
            try {
                cppCompiler.run(INPUT_GENERATOR_EXE_FILE, inputTescaseFilePath, timeout);
                cppCompiler.run(OUTPUT_GENERATOR_EXE_FILE, inputTescaseFilePath, outputTescaseFilePath, timeout);
                GlobalResource.getGenerateTestPanel().increaseDoneTestcase();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } catch (RuntimeErrorException | TimeoutException e) {
                new File(inputTescaseFilePath).delete();
                new File(outputTescaseFilePath).delete();
                GlobalResource.getGenerateTestPanel().stopGenerateTestcase();
                break;
            } catch (CompileErrorException e) {
                e.printStackTrace();
            }
        }
    }
}

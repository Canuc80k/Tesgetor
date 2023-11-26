package com.canuc80k.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import com.canuc80k.compiler.CPPCompiler;
import com.canuc80k.config.Config;
import com.canuc80k.exception.CompileErrorException;
import com.canuc80k.exception.RuntimeErrorException;
import com.canuc80k.exception.TimeoutException;
import com.canuc80k.filetool.FileTool;
import com.canuc80k.launcher.GlobalResource;
import com.canuc80k.testcase.TestcaseFileNameType;

public class CPPGenerator extends Generator {
    private final File INPUT_GENERATOR_EXECUTE_FILE = new File(GlobalResource.getTempFolder().getAbsolutePath() + "/inputGenerator.exe");
    private final File OUTPUT_GENERATOR_EXECUTE_FILE = new File(GlobalResource.getTempFolder().getAbsolutePath() + "/outputGenerator.exe");

    protected CPPCompiler cppCompiler;

    public CPPGenerator() {
        super();
        cppCompiler = new CPPCompiler();
    }

    private synchronized void deleteOldExecuteFiles() {
        FileTool.deleteChildFilesInFolder(GlobalResource.getTempFolder());
    }
    
    @Override
    public synchronized void generate(int beginTestcaseIndex, int endTestcaseIndex, TestcaseFileNameType type, int lastTestcaseFileNameLength, String os, String language, int timeout, Boolean isRunParallel) throws IOException, InterruptedException {
        deleteOldExecuteFiles();
        GlobalResource.getGenerateTestPanel().startCompile();
        Boolean compileSuccessfully = compileGeneratorFiles(language, os);
        if (!compileSuccessfully) {
            GlobalResource.getGenerateTestPanel().startRun();
            return;
        }
        GlobalResource.getGenerateTestPanel().setTotalTestcase(endTestcaseIndex - beginTestcaseIndex + 1);

        errorInformation = "";
        if (isRunParallel) 
            runParallelExcuteFilesToCreateTestcase(beginTestcaseIndex, endTestcaseIndex, type, lastTestcaseFileNameLength, timeout);
        else 
            runSequentiallyExcuteFilesToCreateTestcase(beginTestcaseIndex, endTestcaseIndex, type, lastTestcaseFileNameLength, timeout);
    }
    
    private synchronized Boolean compileGeneratorFiles(String language, String os) throws IOException, InterruptedException {
        try {
            cppCompiler.compile(Config.inputGeneratorFile, INPUT_GENERATOR_EXECUTE_FILE, language, os);
            cppCompiler.compile(Config.outputGeneratorFile, OUTPUT_GENERATOR_EXECUTE_FILE, language, os);
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
            String inputTescaseFilePath = Config.testcaseFolder.getAbsolutePath() + "\\" + TestcaseFileNameType.getFileName(type, i, lastTestcaseFileNameLength) + ".INP";
            String outputTescaseFilePath = Config.testcaseFolder.getAbsolutePath() + "\\" + TestcaseFileNameType.getFileName(type, i, lastTestcaseFileNameLength) + ".OUT";
            
            CPPGeneratorTask inputGeneratorThread = new CPPGeneratorTask(
                cppCompiler,
                INPUT_GENERATOR_EXECUTE_FILE,
                OUTPUT_GENERATOR_EXECUTE_FILE,
                inputTescaseFilePath,
                outputTescaseFilePath,
                timeout
            );
            tasks.add(inputGeneratorThread);
        }
   
        threadPool = Executors.newCachedThreadPool();
        tasks.forEach((task) -> threadPool.execute(task));
        threadPool.shutdown();
    }

    private synchronized void runSequentiallyExcuteFilesToCreateTestcase(int beginTestcaseIndex, int endTestcaseIndex, TestcaseFileNameType type, int lastTestcaseFileNameLength, int timeout) {
        for (int i = beginTestcaseIndex; i <= endTestcaseIndex; i ++) {
            String inputTescaseFilePath = Config.testcaseFolder.getAbsolutePath() + "\\" + TestcaseFileNameType.getFileName(type, i, lastTestcaseFileNameLength) + ".INP";
            String outputTescaseFilePath = Config.testcaseFolder.getAbsolutePath() + "\\" + TestcaseFileNameType.getFileName(type, i, lastTestcaseFileNameLength) + ".OUT";
                
            try {
                cppCompiler.run(INPUT_GENERATOR_EXECUTE_FILE, inputTescaseFilePath, timeout);
                cppCompiler.run(OUTPUT_GENERATOR_EXECUTE_FILE, inputTescaseFilePath, outputTescaseFilePath, timeout);
                GlobalResource.getGenerateTestPanel().increaseDoneTestcase();
            } catch (RuntimeErrorException | TimeoutException e) {
                notifyError(e);
                new File(inputTescaseFilePath).delete();
                new File(outputTescaseFilePath).delete();
                GlobalResource.getGenerateTestPanel().stopGenerateTestcase();
                break;
            } catch (CompileErrorException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
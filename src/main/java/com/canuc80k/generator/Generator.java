package com.canuc80k.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import com.canuc80k.compiler.CPPCompiler;
import com.canuc80k.exception.CompileErrorException;
import com.canuc80k.exception.RuntimeErrorException;
import com.canuc80k.exception.TimeoutException;
import com.canuc80k.filetool.FileTool;
import com.canuc80k.launcher.GlobalResource;
import com.canuc80k.testcase.TestcaseFileNameType;

public class Generator {
    private final File INPUT_GENERATOR_EXE_FILE = new File(GlobalResource.getTempFolder().getAbsolutePath() + "/inputGenerator.exe");
    private final File OUTPUT_GENERATOR_EXE_FILE = new File(GlobalResource.getTempFolder().getAbsolutePath() + "/outputGenerator.exe");

    private File inputGeneratorFile;
    private File outputGeneratorFile;
    private File testcaseFolder;

    private CPPCompiler cppCompiler;
    private ExecutorService threadPool;

    private String errorInformation;

    public Generator() {
        if (!GlobalResource.getTempFolder().exists()) GlobalResource.getTempFolder().mkdirs();

        locateConfigFiles();
        cppCompiler = new CPPCompiler();
    }

    public synchronized void locateConfigFiles() {
        List<String> configData = GlobalResource.getConfigData();
        inputGeneratorFile = new File(configData.get(0));
        outputGeneratorFile = new File(configData.get(1));
        testcaseFolder = new File(configData.get(2));
    }

    public synchronized void generate(int beginTestcaseIndex, int endTestcaseIndex, TestcaseFileNameType type, int lastTestcaseFileNameLength) throws IOException, InterruptedException {
        locateConfigFiles();
        FileTool.deleteFolder(GlobalResource.getTempFolder(), FileTool.KEEP_CURRENT_FOLDER);
        try {
            cppCompiler.compile_gplusplus(inputGeneratorFile, INPUT_GENERATOR_EXE_FILE);
            cppCompiler.compile_gplusplus(outputGeneratorFile, OUTPUT_GENERATOR_EXE_FILE);
        } catch (CompileErrorException | TimeoutException | RuntimeErrorException e) {
            JOptionPane.showMessageDialog(
                null, 
                "Errors occur when compile testcase generator files",
                "Check your testcase generator files",
                JOptionPane.NO_OPTION
            );
            return;
        }
        GlobalResource.getGenerateTestPanel().setTotalTestcase(endTestcaseIndex - beginTestcaseIndex + 1);

        generateTestcases(beginTestcaseIndex, endTestcaseIndex, type, lastTestcaseFileNameLength);
    }

    public synchronized void clear() {
        FileTool.deleteFolder(testcaseFolder, FileTool.KEEP_CURRENT_FOLDER);
        new File(testcaseFolder.getAbsolutePath() + ".zip").delete();
    }

    private synchronized void generateTestcases(int beginTestcaseIndex, int endTestcaseIndex, TestcaseFileNameType type, int lastTestcaseFileNameLength) throws IOException, InterruptedException { 
        List<GeneratorTask> tasks = new ArrayList<GeneratorTask>();
        for (int i = beginTestcaseIndex; i <= endTestcaseIndex; i ++) {
            GeneratorTask inputGeneratorThread 
                = new GeneratorTask(
                    cppCompiler, 
                    INPUT_GENERATOR_EXE_FILE, 
                    OUTPUT_GENERATOR_EXE_FILE,
                    testcaseFolder.getAbsolutePath() + "\\" + TestcaseFileNameType.getFileName(type, i, lastTestcaseFileNameLength) + ".INP",
                    testcaseFolder.getAbsolutePath() + "\\" + TestcaseFileNameType.getFileName(type, i, lastTestcaseFileNameLength) + ".OUT"
                );
            tasks.add(inputGeneratorThread);
        }

        errorInformation = "";
        threadPool = Executors.newCachedThreadPool();  
        tasks.forEach((task) -> threadPool.execute(task));
        threadPool.shutdown();
    }

    public synchronized void notifyError(Exception e) {
        errorInformation = e.getClass().getSimpleName();
    }

    public String getErrorInformation() {
        return errorInformation;
    }
}

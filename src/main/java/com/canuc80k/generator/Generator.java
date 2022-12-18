package com.canuc80k.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.canuc80k.compiler.CPPCompiler;
import com.canuc80k.userinterface.ConfigPanel;

import filetool.FileTool;

public class Generator {
    private final File TEMP_FOLDER = new File("temp");
    private final File INPUT_GENERATOR_EXE_FILE = new File(TEMP_FOLDER.getAbsolutePath() + "/inputGenerator.exe");
    private final File OUTPUT_GENERATOR_EXE_FILE = new File(TEMP_FOLDER.getAbsolutePath() + "/outputGenerator.exe");

    private File inputGeneratorFile;
    private File outputGeneratorFile;
    private File testcaseFolder;

    private CPPCompiler cppCompiler;

    public Generator() {
        if (!TEMP_FOLDER.exists()) TEMP_FOLDER.mkdirs();

        List<String> configData = ConfigPanel.getConfigData();
        inputGeneratorFile = new File(configData.get(0));
        outputGeneratorFile = new File(configData.get(1));
        testcaseFolder = new File(configData.get(2));
        
        cppCompiler = new CPPCompiler();
    }

    public synchronized void generate(int beginTestcaseIndex, int endTestcaseIndex) throws IOException, InterruptedException {
        cppCompiler.compile_gplusplus(inputGeneratorFile, INPUT_GENERATOR_EXE_FILE);
        cppCompiler.compile_gplusplus(outputGeneratorFile, OUTPUT_GENERATOR_EXE_FILE);

        generateTestcases(beginTestcaseIndex, endTestcaseIndex);
    }

    public synchronized void clear() {   
        FileTool.deleteFolder(TEMP_FOLDER, FileTool.KEEP_CURRENT_FOLDER);
        FileTool.deleteFolder(testcaseFolder, FileTool.KEEP_CURRENT_FOLDER);
    }

    private synchronized void generateTestcases(int beginTestcaseIndex, int endTestcaseIndex) throws IOException, InterruptedException { 
        List<Runnable> tasks = new ArrayList<Runnable>();
        for (int i = beginTestcaseIndex; i <= endTestcaseIndex; i ++) {
            Runnable inputGeneratorThread 
                = new GeneratorTask(
                    cppCompiler, 
                    INPUT_GENERATOR_EXE_FILE, 
                    OUTPUT_GENERATOR_EXE_FILE,
                    testcaseFolder.getAbsolutePath() + "\\" + i + ".INP",
                    testcaseFolder.getAbsolutePath() + "\\" + i + ".OUT"
                );
            tasks.add(inputGeneratorThread);
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(20);  
        tasks.forEach((task) -> threadPool.execute(task));
        threadPool.shutdown();
    }
}

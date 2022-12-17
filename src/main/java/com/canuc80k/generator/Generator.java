package com.canuc80k.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.canuc80k.compiler.CPPCompiler;
import com.canuc80k.userinterface.ConfigPanel;

public class Generator {
    private final File TEMP_FOLDER = new File("temp");
    private final File INPUT_GENERATOR_EXE_FILE = new File(TEMP_FOLDER.getAbsolutePath() + "/inputGenerator.exe");
    private final File OUTPUT_GENERATOR_EXE_FILE = new File(TEMP_FOLDER.getAbsolutePath() + "/outputGenerator.exe");

    private File inputGeneratorFile;
    private File outputGeneratorFile;
    private File testcaseFolder;

    public Generator() {
        List<String> configData = ConfigPanel.getConfigData();
        inputGeneratorFile = new File(configData.get(0));
        outputGeneratorFile = new File(configData.get(1));
        testcaseFolder = new File(configData.get(2));
    }

    public synchronized void generate(int beginTestcaseIndex, int endTestcaseIndex) throws IOException, InterruptedException {
        CPPCompiler cppCompiler = new CPPCompiler();
        cppCompiler.compile_gplusplus(inputGeneratorFile, INPUT_GENERATOR_EXE_FILE);
        cppCompiler.compile_gplusplus(outputGeneratorFile, OUTPUT_GENERATOR_EXE_FILE);

        generateInputTestcase(beginTestcaseIndex, endTestcaseIndex);
        generateOutputTestcase(beginTestcaseIndex, endTestcaseIndex);
    }

    private synchronized void generateInputTestcase(int beginTestcaseIndex, int endTestcaseIndex) throws IOException, InterruptedException { 
        CPPCompiler cppCompiler = new CPPCompiler();
        for (int i = beginTestcaseIndex; i <= endTestcaseIndex; i ++) {
            cppCompiler.run(INPUT_GENERATOR_EXE_FILE, testcaseFolder.getAbsolutePath() + "\\" + i + ".INP");
        }
    }

    private synchronized void generateOutputTestcase(int beginTestcaseIndex, int endTestcaseIndex) throws IOException, InterruptedException {
        CPPCompiler cppCompiler = new CPPCompiler();
        for (int i = beginTestcaseIndex; i <= endTestcaseIndex; i ++) {
            cppCompiler.run(OUTPUT_GENERATOR_EXE_FILE, testcaseFolder.getAbsolutePath() + "\\" + i + ".INP", testcaseFolder.getAbsolutePath() + "\\" + i + ".OUT");
        }
    }
}

package com.canuc80k.generator;

import java.io.File;
import java.io.IOException;

import com.canuc80k.compiler.CPPCompiler;
import com.canuc80k.exception.CompileErrorException;
import com.canuc80k.exception.RuntimeErrorException;
import com.canuc80k.exception.TimeoutException;
import com.canuc80k.launcher.GlobalResource;

public class GeneratorTask implements Runnable {
    private final File inputgeneratorExeFile;
    private final File outputgeneratorExeFile;
    private CPPCompiler cppCompiler;

    private String inputTescaseFilePath, outputTescaseFilePath;

    GeneratorTask(CPPCompiler cppCompiler, File inputgeneratorExeFile, File outputgeneratorExeFile, String inputTescaseFilePath, String outputTescaseFilePath) {
        this.cppCompiler = cppCompiler;
        this.inputgeneratorExeFile = inputgeneratorExeFile;
        this.outputgeneratorExeFile = outputgeneratorExeFile;
        this.inputTescaseFilePath = inputTescaseFilePath;
        this.outputTescaseFilePath = outputTescaseFilePath;
    }

    @Override
    public synchronized void run() {
        try {
            cppCompiler.run(inputgeneratorExeFile, inputTescaseFilePath);
            cppCompiler.run(outputgeneratorExeFile, inputTescaseFilePath, outputTescaseFilePath);
            GlobalResource.getGenerateTestPanel().increaseDoneTestcase();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (RuntimeErrorException e) {
            GlobalResource.getGenerator().notifyError(e);
            new File(inputTescaseFilePath).delete();
            new File(outputTescaseFilePath).delete();
            GlobalResource.getGenerateTestPanel().increaseDoneTestcase();
        } catch (TimeoutException e) {
            GlobalResource.getGenerator().notifyError(e);
            new File(inputTescaseFilePath).delete();
            new File(outputTescaseFilePath).delete();
            GlobalResource.getGenerateTestPanel().increaseDoneTestcase();
        } catch (CompileErrorException e) {
            e.printStackTrace();
        }
    }
}

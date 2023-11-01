package com.canuc80k.generator;

import java.io.File;
import java.io.IOException;

import com.canuc80k.compiler.CPPCompiler;
import com.canuc80k.exception.CompileErrorException;
import com.canuc80k.exception.RuntimeErrorException;
import com.canuc80k.exception.TimeoutException;
import com.canuc80k.launcher.GlobalResource;

public class CPPGeneratorTask implements Runnable {
    private final File inputgeneratorExeFile;
    private final File outputgeneratorExeFile;
    private CPPCompiler compiler;

    private String inputTescaseFilePath, outputTescaseFilePath;

    CPPGeneratorTask(CPPCompiler compiler, File inputgeneratorExeFile, File outputgeneratorExeFile, String inputTescaseFilePath, String outputTescaseFilePath) {
        this.compiler = compiler;
        this.inputgeneratorExeFile = inputgeneratorExeFile;
        this.outputgeneratorExeFile = outputgeneratorExeFile;
        this.inputTescaseFilePath = inputTescaseFilePath;
        this.outputTescaseFilePath = outputTescaseFilePath;
    }

    @Override
    public synchronized void run() {
        try {
            compiler.run(inputgeneratorExeFile, inputTescaseFilePath);
            compiler.run(outputgeneratorExeFile, inputTescaseFilePath, outputTescaseFilePath);
            GlobalResource.getGenerateTestPanel().increaseDoneTestcase();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (RuntimeErrorException | TimeoutException e) {
            GlobalResource.getCPPGenerator().notifyError(e);
            new File(inputTescaseFilePath).delete();
            new File(outputTescaseFilePath).delete();
            GlobalResource.getGenerateTestPanel().increaseDoneTestcase();
        } catch (CompileErrorException e) {
            e.printStackTrace();
        }
    }
}
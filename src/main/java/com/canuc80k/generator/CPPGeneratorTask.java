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
    private int timeout;

    private String inputTescaseFilePath, outputTescaseFilePath;

    CPPGeneratorTask(CPPCompiler compiler, File inputgeneratorExeFile, File outputgeneratorExeFile, String inputTescaseFilePath, String outputTescaseFilePath, int timeout) {
        this.compiler = compiler;
        this.inputgeneratorExeFile = inputgeneratorExeFile;
        this.outputgeneratorExeFile = outputgeneratorExeFile;
        this.inputTescaseFilePath = inputTescaseFilePath;
        this.outputTescaseFilePath = outputTescaseFilePath;
        this.timeout = timeout;
    }

    @Override
    public synchronized void run() {        
        try {
            compiler.run(inputgeneratorExeFile, inputTescaseFilePath, timeout);
            compiler.run(outputgeneratorExeFile, inputTescaseFilePath, outputTescaseFilePath, timeout);
            GlobalResource.getGenerateTestPanel().increaseDoneTestcase();
        } catch (RuntimeErrorException | TimeoutException e) {
            GlobalResource.getCPPGenerator().notifyError(e);
            new File(inputTescaseFilePath).delete();
            new File(outputTescaseFilePath).delete();
            GlobalResource.getGenerateTestPanel().increaseDoneTestcase();
        } catch (CompileErrorException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
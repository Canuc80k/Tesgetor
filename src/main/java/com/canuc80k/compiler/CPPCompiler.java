package com.canuc80k.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.canuc80k.exception.CompileErrorException;
import com.canuc80k.exception.RuntimeErrorException;
import com.canuc80k.exception.TimeoutException;

public class CPPCompiler extends Compiler {
    public CPPCompiler() {
        super();    
        List<String> initCommands = new ArrayList<String>();
        initCommands.add("cmd.exe");
        initCommands.add("/c");
        setCommands(initCommands);
    }

    public synchronized void compile_gplusplus(File sourceFile, File outputFile) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        String compileCommand = "g++ -std=c++11 \"" + sourceFile.getAbsolutePath() + "\" -o " + "\"" + outputFile.getAbsolutePath() + "\"";
        executeCommand(compileCommand, CompileAction.COMPILE);
    }

    public synchronized void run(File runFile) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        String runCommand = runFile.getAbsolutePath();
        executeCommand(runCommand, CompileAction.RUN);
    }

    public synchronized void run(File runFile, String arg) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        String runCommand = "c: && \"" + runFile.getAbsolutePath() + "\" \"" + arg + "\"";
        executeCommand(runCommand, CompileAction.RUN);
    }

    public synchronized void run(File runFile, String arg1, String arg2) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        String runCommand = "c: && \"" + runFile.getAbsolutePath() + "\" \"" + arg1 + "\" \"" + arg2 + "\"";
        executeCommand(runCommand, CompileAction.RUN);
    }
}

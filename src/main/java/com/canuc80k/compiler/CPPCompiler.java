package com.canuc80k.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.canuc80k.constant.LanguageConstant;
import com.canuc80k.constant.OsConstant;
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

    public synchronized void compile(File sourceFile, File outputFile, String language, String os) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        String cppversion = LanguageConstant.getExecuteCommand(language);
        String compileCommand = "";
        if (os.equals(OsConstant.WINDOW))  
            compileCommand = "g++ --O2 -s -static -Wl,--stack,66060288 -lm -x c++ " + cppversion + " \"" + sourceFile.getAbsolutePath() + "\" -o " + "\"" + outputFile.getAbsolutePath() + "\"";

        System.out.println(compileCommand);
        compile(compileCommand);
    }

    public synchronized void run(File runFile, int timeout) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        String runCommand = runFile.getAbsolutePath();
        run(runCommand, timeout);
    }

    public synchronized void run(File runFile, String arg, int timeout) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        String runCommand = "c: && \"" + runFile.getAbsolutePath() + "\" \"" + arg + "\"";
        run(runCommand, timeout);
    }

    public synchronized void run(File runFile, String arg1, String arg2, int timeout) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        String runCommand = "c: && \"" + runFile.getAbsolutePath() + "\" \"" + arg1 + "\" \"" + arg2 + "\"";
        run(runCommand, timeout);
    }
}

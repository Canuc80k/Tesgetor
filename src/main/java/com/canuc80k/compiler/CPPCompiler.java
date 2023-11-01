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
            compileCommand = "g++ " + cppversion + " \"" + sourceFile.getAbsolutePath() + "\" -o " + "\"" + outputFile.getAbsolutePath() + "\"";

        System.out.println(compileCommand);
        compile(compileCommand);
    }

    public synchronized void run(File runFile) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        String runCommand = runFile.getAbsolutePath();
        run(runCommand);
    }

    public synchronized void run(File runFile, String arg) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        String runCommand = "c: && \"" + runFile.getAbsolutePath() + "\" \"" + arg + "\"";
        run(runCommand);
    }

    public synchronized void run(File runFile, String arg1, String arg2) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        String runCommand = "c: && \"" + runFile.getAbsolutePath() + "\" \"" + arg1 + "\" \"" + arg2 + "\"";
        run(runCommand);
    }
}

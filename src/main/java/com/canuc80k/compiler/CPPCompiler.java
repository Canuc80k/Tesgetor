package com.canuc80k.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CPPCompiler extends Compiler {
    private List<String> commands;
    
    public CPPCompiler() {
        super();    
        commands = new ArrayList<>();
        commands.add("cmd.exe");
        commands.add("/c");
    }

    public synchronized void compile_gplusplus(File sourceFile, File outputFile) throws IOException, InterruptedException {
        String compileCommand = "g++ " + sourceFile.getAbsolutePath() + " -o " + "\"" + outputFile.getAbsolutePath() + "\"";
        commands.add(compileCommand);

        builder.command(commands).start().waitFor();
        commands.remove(commands.size() - 1);
    }

    public synchronized void run(File runFile) throws IOException, InterruptedException {
        String runCommand = runFile.getAbsolutePath();
        commands.add(runCommand);

        builder.command(commands).start().waitFor();
        commands.remove(commands.size() - 1);
    }
}

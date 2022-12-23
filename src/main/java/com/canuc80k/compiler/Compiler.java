package com.canuc80k.compiler;

import java.io.IOException;
import java.util.List;

import com.canuc80k.exception.CompileErrorException;

public abstract class Compiler {
    protected ProcessBuilder builder;
    private List<String> commands;

    Compiler() {
        builder = new ProcessBuilder();
    }

    public synchronized void executeCommand(String command) throws IOException, InterruptedException, CompileErrorException {
        commands.add(command);

        int exit_code = builder.command(commands).start().waitFor();
        commands.remove(commands.size() - 1);
        
        if (exit_code != 0) throw new CompileErrorException();
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> newCommands) {
        commands = newCommands;
    }
}

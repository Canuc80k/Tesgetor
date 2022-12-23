package com.canuc80k.compiler;

import java.io.IOException;
import java.util.List;

public abstract class Compiler {
    protected ProcessBuilder builder;
    private List<String> commands;

    Compiler() {
        builder = new ProcessBuilder();
    }

    public synchronized void executeCommand(String command) throws IOException, InterruptedException {
        commands.add(command);

        builder.command(commands).inheritIO().start().waitFor();
        commands.remove(commands.size() - 1);
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> newCommands) {
        commands = newCommands;
    }
}

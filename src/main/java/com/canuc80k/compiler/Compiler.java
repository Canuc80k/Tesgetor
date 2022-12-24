package com.canuc80k.compiler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.canuc80k.exception.CompileErrorException;
import com.canuc80k.exception.RuntimeErrorException;
import com.canuc80k.exception.TimeoutException;

public abstract class Compiler {
    private static int TIME_OUT_SECONDS = 5;

    protected ProcessBuilder builder;
    private List<String> commands;

    Compiler() {
        builder = new ProcessBuilder();
    }

    public synchronized void executeCommand(String command, CompileAction action) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        commands.add(command);

        Process process = builder.command(commands).start();
        boolean isRunCompletely = process.waitFor(TIME_OUT_SECONDS, TimeUnit.SECONDS);
        commands.remove(commands.size() - 1);
        
        if (!isRunCompletely) {
            Runtime.getRuntime().exec("taskkill /f /t /im inputGenerator.exe").waitFor();
            Runtime.getRuntime().exec("taskkill /f /t /im outputGenerator.exe").waitFor();

            process.destroy();
            throw new TimeoutException();
        }

        int exit_code = process.exitValue();
        if (exit_code != 0) {
            if (action == CompileAction.COMPILE) {
                throw new CompileErrorException();
            }
            throw new RuntimeErrorException();
        }
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> newCommands) {
        commands = newCommands;
    }
}

package com.canuc80k.compiler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.canuc80k.exception.CompileErrorException;
import com.canuc80k.exception.RuntimeErrorException;
import com.canuc80k.exception.TimeoutException;

public abstract class Compiler {
    protected ProcessBuilder builder;
    private List<String> commands;

    Compiler() {
        builder = new ProcessBuilder();
    }

    public synchronized void compile(String command) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        executeCommand(command, CompileAction.COMPILE, Integer.MAX_VALUE);
    }

    public synchronized void run(String command, int timeout) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        executeCommand(command, CompileAction.RUN, timeout);
    }

    private synchronized void executeCommand(String command, CompileAction action, int timeout) throws IOException, InterruptedException, CompileErrorException, TimeoutException, RuntimeErrorException {
        commands.add(command);

        Process process = builder.command(commands).start();
        boolean isRunCompletely = process.waitFor(timeout, TimeUnit.SECONDS);
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

package com.canuc80k.compiler;

public abstract class Compiler {
    protected ProcessBuilder builder;
    
    Compiler() {
        builder = new ProcessBuilder();
    }
}

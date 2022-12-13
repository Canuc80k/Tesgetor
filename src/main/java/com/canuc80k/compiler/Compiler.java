package com.canuc80k.compiler;

public abstract class Compiler {
    ProcessBuilder builder;
    
    Compiler() {
        builder = new ProcessBuilder();
    }
}

package com.canuc80k.launcher;

import java.io.File;
import java.io.IOException;

import com.canuc80k.compiler.CPPCompiler;

public class Launcher {
    public static void main(String[] args) {
        try {
            CPPCompiler a = new CPPCompiler();
            a.compile_gplusplus(
                new File("C:\\Users\\Lenovo\\Desktop\\a.cpp"),
                new File("C:\\Users\\Lenovo\\Desktop\\b\\a.exe")
            );
            a.run(
                new File("C:\\Users\\Lenovo\\Desktop\\b\\a.exe")
            );
            a.run(
                new File("C:\\Users\\Lenovo\\Desktop\\b\\a.exe")
            );
            a.run(
                new File("C:\\Users\\Lenovo\\Desktop\\b\\a.exe")
            );
            a.run(
                new File("C:\\Users\\Lenovo\\Desktop\\b\\a.exe")
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

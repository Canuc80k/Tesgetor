package com.canuc80k.launcher;

import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.canuc80k.compiler.CPPCompiler;
import com.canuc80k.font.OpenSansFontInitor;
import com.canuc80k.userinterface.HomeFrame;

public class Launcher {
    public static void main(String[] args) {
        try {
            new OpenSansFontInitor().initFont();

            HomeFrame homeFrame = new HomeFrame();
            homeFrame.setVisible(true);

            CPPCompiler a = new CPPCompiler();
            a.compile_gplusplus(
                    new File("C:\\Users\\Lenovo\\Desktop\\a.cpp"),
                    new File("C:\\Users\\Lenovo\\Desktop\\b\\a.exe"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

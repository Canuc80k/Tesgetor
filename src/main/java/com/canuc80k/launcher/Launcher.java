package com.canuc80k.launcher;

import com.canuc80k.font.OpenSansFontInitor;
import com.canuc80k.theme.DracularTheme;
import com.canuc80k.userinterface.HomeFrame;
public class Launcher {
    public static void main(String[] args) {
        new DracularTheme().init();
        new OpenSansFontInitor().initFont();

        HomeFrame homeFrame = new HomeFrame();
        homeFrame.pack();
        homeFrame.setVisible(true);
    }
}

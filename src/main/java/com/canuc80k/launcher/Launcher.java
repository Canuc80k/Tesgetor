package com.canuc80k.launcher;

import com.canuc80k.userinterface.HomeFrame;

public class Launcher {
    public static void main(String[] args) {
        GlobalResource.init();

        HomeFrame homeFrame = new HomeFrame();
        homeFrame.pack();
        homeFrame.setVisible(true);
    }
}

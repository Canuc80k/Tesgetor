package com.canuc80k.theme;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DracularTheme extends Theme {
    public void init() {
        super.init();
        
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatDraculaIJTheme");
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 20);
            UIManager.put("ProgressBar.arc", 20);
            UIManager.put("TextComponent.arc", 20);
            UIManager.put("Button.foreground", Color.white);
            UIManager.put("ScrollBar.thumbArc", 100);
            UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException| UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}

package com.canuc80k.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class FontInitor {	
    private GraphicsEnvironment ge;

    FontInitor() {
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    }

    protected void initFont(List<File> fonts) {
        try {
            for (File font : fonts) {
                System.out.println(Font.createFont(Font.TRUETYPE_FONT, font));
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font));
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}

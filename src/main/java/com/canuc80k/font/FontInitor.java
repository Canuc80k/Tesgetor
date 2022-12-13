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

    protected void initFont(List<String> font_paths) {
        try {
            for (String font_path : font_paths)
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(font_path)));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}

package com.canuc80k.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class OpenSansFontInitor {	
    private final static URL OPEN_SANS_FONTS_FOLDER_URL = OpenSansFontInitor.class.getResource("/font/OpenSans");

    public final static void initFont() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            final File OPEN_SANS_FONTS_FOLDER = new File(OPEN_SANS_FONTS_FOLDER_URL.toURI()); 
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(OPEN_SANS_FONTS_FOLDER.getAbsolutePath() + "\\OpenSans-Bold.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(OPEN_SANS_FONTS_FOLDER.getAbsolutePath() + "\\OpenSans-ExtraBold.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(OPEN_SANS_FONTS_FOLDER.getAbsolutePath() + "\\OpenSans-Light.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(OPEN_SANS_FONTS_FOLDER.getAbsolutePath() + "\\OpenSans-Medium.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(OPEN_SANS_FONTS_FOLDER.getAbsolutePath() + "\\OpenSans-Regular.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(OPEN_SANS_FONTS_FOLDER.getAbsolutePath() + "\\OpenSans-SemiBold.ttf")));
        } catch (FontFormatException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}

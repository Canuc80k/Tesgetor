package com.canuc80k.font;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OpenSansFontInitor extends FontInitor {
    private final URL OPEN_SANS_FONTS_FOLDER_URL = OpenSansFontInitor.class.getResource("/font/OpenSans");
    private List<String> font_paths;
    
    public OpenSansFontInitor() {
        super();
        font_paths = new ArrayList<String>();

        try {
            File OPEN_SANS_FONTS_FOLDER = new File(OPEN_SANS_FONTS_FOLDER_URL.toURI());
            font_paths.add(OPEN_SANS_FONTS_FOLDER.getAbsolutePath() + "\\OpenSans-Bold.ttf");
            font_paths.add(OPEN_SANS_FONTS_FOLDER.getAbsolutePath() + "\\OpenSans-Light.ttf");
            font_paths.add(OPEN_SANS_FONTS_FOLDER.getAbsolutePath() + "\\OpenSans-ExtraBold.ttf");
            font_paths.add(OPEN_SANS_FONTS_FOLDER.getAbsolutePath() + "\\OpenSans-Medium.ttf");
            font_paths.add(OPEN_SANS_FONTS_FOLDER.getAbsolutePath() + "\\OpenSans-Regular.ttf");
            font_paths.add(OPEN_SANS_FONTS_FOLDER.getAbsolutePath() + "\\OpenSans-SemiBold.ttf");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } 
    }

    public void initFont() {
        super.initFont(font_paths);
    }
}

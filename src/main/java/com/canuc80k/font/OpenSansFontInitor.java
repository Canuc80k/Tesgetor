package com.canuc80k.font;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OpenSansFontInitor extends FontInitor {
    private List<File> fonts;

    public OpenSansFontInitor() {
        super();
        fonts = new ArrayList<File>();

        fonts.add(new File("font/OpenSans/OpenSans-Bold.ttf"));
        fonts.add(new File("font/OpenSans/OpenSans-ExtraBold.ttf"));
        fonts.add(new File("font/OpenSans/OpenSans-Light.ttf"));
        fonts.add(new File("font/OpenSans/OpenSans-Medium.ttf"));
        fonts.add(new File("font/OpenSans/OpenSans-Regular.ttf"));
        fonts.add(new File("font/OpenSans/OpenSans-SemiBold.ttf"));
    }

    public void initFont() {
        super.initFont(fonts);
    }
}

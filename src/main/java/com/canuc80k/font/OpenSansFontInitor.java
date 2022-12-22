package com.canuc80k.font;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class OpenSansFontInitor extends FontInitor {
    private List<File> fonts;

    private File inputStreamtoFile(InputStream inputStream, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            IOUtils.copy(inputStream, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public OpenSansFontInitor() {
        super();
        fonts = new ArrayList<File>();

        File tempFolder = new File("/temp");
        if (!tempFolder.exists()) tempFolder.mkdirs();
        
        fonts.add(inputStreamtoFile(OpenSansFontInitor.class.getResourceAsStream("/font/OpenSans/OpenSans-Bold.ttf"), new File("/temp/OpenSans-Bold.ttf")));
        fonts.add(inputStreamtoFile(OpenSansFontInitor.class.getResourceAsStream("/font/OpenSans/OpenSans-ExtraBold.ttf"), new File("/temp/OpenSans-ExtraBold.ttf")));
        fonts.add(inputStreamtoFile(OpenSansFontInitor.class.getResourceAsStream("/font/OpenSans/OpenSans-SemiBold.ttf"), new File("/temp/OpenSans-SemiBold.ttf")));
        fonts.add(inputStreamtoFile(OpenSansFontInitor.class.getResourceAsStream("/font/OpenSans/OpenSans-Regular.ttf"), new File("/temp/OpenSans-Regular.ttf")));
        fonts.add(inputStreamtoFile(OpenSansFontInitor.class.getResourceAsStream("/font/OpenSans/OpenSans-Medium.ttf"), new File("/temp/OpenSans-Medium.ttf")));
        fonts.add(inputStreamtoFile(OpenSansFontInitor.class.getResourceAsStream("/font/OpenSans/OpenSans-Light.ttf"), new File("/temp/OpenSans-Light.ttf")));
    }

    public void initFont() {
        super.initFont(fonts);
    }
}

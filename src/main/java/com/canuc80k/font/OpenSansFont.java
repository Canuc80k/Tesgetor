package com.canuc80k.font;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.canuc80k.datastructure.Pair;
import com.canuc80k.filetool.FileTool;
import com.canuc80k.launcher.GlobalResource;

public class OpenSansFont extends ExtendedFont {
    private List<Pair> fonts, sizes;

    public void init() {
        fonts = new ArrayList<Pair>();
        sizes = new ArrayList<Pair>();
        
        fonts.add(new Pair(FileTool.inputStreamtoFile(OpenSansFont.class.getResourceAsStream("/font/OpenSans/OpenSans-Bold.ttf"), new File(GlobalResource.getTempFolder().getAbsolutePath() + "/OpenSans-Bold.ttf")), FontType.BOLD));
        fonts.add(new Pair(FileTool.inputStreamtoFile(OpenSansFont.class.getResourceAsStream("/font/OpenSans/OpenSans-ExtraBold.ttf"), new File(GlobalResource.getTempFolder().getAbsolutePath() + "/OpenSans-ExtraBold.ttf")), FontType.EXTRA_BOLD));
        fonts.add(new Pair(FileTool.inputStreamtoFile(OpenSansFont.class.getResourceAsStream("/font/OpenSans/OpenSans-SemiBold.ttf"), new File(GlobalResource.getTempFolder().getAbsolutePath() + "/OpenSans-SemiBold.ttf")), FontType.SEMI_BOLD));
        fonts.add(new Pair(FileTool.inputStreamtoFile(OpenSansFont.class.getResourceAsStream("/font/OpenSans/OpenSans-Regular.ttf"), new File(GlobalResource.getTempFolder().getAbsolutePath() + "/OpenSans-Regular.ttf")), FontType.REGULAR));
        fonts.add(new Pair(FileTool.inputStreamtoFile(OpenSansFont.class.getResourceAsStream("/font/OpenSans/OpenSans-Medium.ttf"), new File(GlobalResource.getTempFolder().getAbsolutePath() + "/OpenSans-Medium.ttf")), FontType.MEDIUM));
        fonts.add(new Pair(FileTool.inputStreamtoFile(OpenSansFont.class.getResourceAsStream("/font/OpenSans/OpenSans-Light.ttf"), new File(GlobalResource.getTempFolder().getAbsolutePath() + "/OpenSans-Light.ttf")), FontType.LIGHT));
    
        sizes.add(new Pair(16.0f, FontSize.LARGE));
        sizes.add(new Pair(14.0f, FontSize.MEDIUM));
        sizes.add(new Pair(12.0f, FontSize.SMALL));

        super.initFont(fonts);
        super.initSize(sizes);
    }
}

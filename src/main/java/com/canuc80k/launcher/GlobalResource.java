package com.canuc80k.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.canuc80k.font.ExtendedFont;
import com.canuc80k.font.OpenSansFont;
import com.canuc80k.theme.DracularTheme;
import com.canuc80k.theme.Theme;
import com.canuc80k.userinterface.ConfigPanel;
import com.canuc80k.userinterface.GenerateTestPanel;
import com.canuc80k.userinterface.InitPanel;

@SuppressWarnings("unchecked")
public class GlobalResource {
    public static final int OUTPUT_GENERATEOR_INDEX = 0;
    public static final int INPUT_GENERATOR_INDEX = 1;
    public static final int TESTCASE_FOLDER_INDEX = 2;

    private static File configFolder;
    private static File configFile;
    private static File tempFolder;
    private static File projectFolder;

    private static Theme theme;
    private static ExtendedFont font;
    
    private static GenerateTestPanel generateTestPanel;
    private static ConfigPanel configPanel;
    private static InitPanel initPanel;
    private static List<String> configData;

    protected static void init() {
        DracularTheme dracularTheme = new DracularTheme();
        dracularTheme.applyTheme();

        theme = dracularTheme;
    
        OpenSansFont openSansFont = new OpenSansFont();
        openSansFont.init();
        
        font = openSansFont;

        projectFolder = new File(System.getenv("LOCALAPPDATA") + "/dont_generatetest_me_pls");
        if (!projectFolder.exists()) projectFolder.mkdirs();

        tempFolder = new File(projectFolder.getAbsolutePath() + "/temp");
        if (!tempFolder.exists()) tempFolder.mkdirs();

        configFolder = new File(projectFolder.getAbsolutePath() + "/config");
        if (!configFolder.exists()) configFolder.mkdirs();
        
        configFile = new File(configFolder.getAbsolutePath() + "/config.cfg");

        configPanel = new ConfigPanel();
        generateTestPanel = new GenerateTestPanel();
        initPanel = new InitPanel();
    }

    public static Theme getTheme() {
        return theme;
    }

    public static ExtendedFont getFont() {
        return font;
    }

    public static File getTempFolder() {
        return tempFolder;
    }

    public static File getConfigFile() {
        return configFile;
    }

    public static File getConfigFolder() {
        return configFolder;
    }

    public static ConfigPanel getConfigPanel() {
        return configPanel;
    }
    
    public static GenerateTestPanel getGenerateTestPanel() {
        return generateTestPanel;
    }

    public static InitPanel getInitPanel() {
        return initPanel;
    }

    public static List<String> getConfigData() {
        return configData;
    }

    public static void serializeConfigData() {
        try {
            ObjectOutputStream configFileObjectOutputStream = new ObjectOutputStream(new FileOutputStream(GlobalResource.getConfigFile()));
            configFileObjectOutputStream.writeObject(configData);
            configFileObjectOutputStream.flush();
            configFileObjectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeConfigData() {
        try {
            ObjectInputStream configFileObjectInputStream = new ObjectInputStream(new FileInputStream(GlobalResource.getConfigFile()));
            configData = (List<String>) configFileObjectInputStream.readObject();
            configFileObjectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            configData = new ArrayList<String>();
            for (int i = 0; i < 3; i ++) configData.add("");
        }
    }
}

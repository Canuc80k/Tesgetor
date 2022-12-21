package com.canuc80k.theme;
import java.awt.Color;

public class ThemeProperty {
    private static Color backgroundColor = Color.decode("#282a36");
    private static Color topPannelColor = Color.decode("#94959b");
    private static Color singleOptionPannelColor = Color.decode("#eeeeee");
    private static Color fontColor = Color.decode("#000000");
    private static Color fontHighLightColor = Color.decode("#fd93b7");
    public static Color getBackgroundColor() {
        return backgroundColor;
    }
    
    public static Color getTopPanelColor() {
        return topPannelColor;
    }

    public static Color getSingleOptionPannelColor() {
        return singleOptionPannelColor;
    }

    public static Color getFontColor() {
        return fontColor;
    }

    public static Color getFontHighLightColor() {
        return fontHighLightColor;
    }
}

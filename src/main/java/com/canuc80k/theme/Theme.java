package com.canuc80k.theme;
import java.awt.Color;

public class Theme {
    private Color backgroundColor;
    private Color topPanelColor;
    private Color singleOptionPannelColor;
    private Color fontColor;
    private Color fontHighLightColor;
    private Color inverseFontColor;

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    protected void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public Color getTopPanelColor() {
        return topPanelColor;
    }

    protected void setTopPanelColor(Color topPanelColor) {
        this.topPanelColor = topPanelColor;
    }

    public Color getSingleOptionPannelColor() {
        return singleOptionPannelColor;
    }

    protected void setSingleOptionPannelColor(Color singleOptionPannelColor) {
        this.singleOptionPannelColor = singleOptionPannelColor;
    }

    public Color getFontColor() {
        return fontColor;
    }

    protected void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public Color getFontHighLightColor() {
        return fontHighLightColor;
    }
    
    protected void setFontHighLightColor(Color fontHighLightColor) {
        this.fontHighLightColor = fontHighLightColor;
    }

    public Color getInverseFontColor() {
        return inverseFontColor;
    }

    protected void setInverseFontColor(Color inverseFontColor) {
        this.inverseFontColor = inverseFontColor;
    }
}

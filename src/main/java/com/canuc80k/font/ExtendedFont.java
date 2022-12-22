package com.canuc80k.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.datastructure.Pair;

public class ExtendedFont {
    private Font boldFont;
    private Font extraBoldFont;
    private Font semiBoldFont;
    private Font lightFont;
    private Font regularFont;
    private Font mediumFont;

    private float largeSize;
    private float mediumSize;
    private float smallSize;

    private static final GraphicsEnvironment GRAPHICS_ENVIRONMENT = GraphicsEnvironment.getLocalGraphicsEnvironment();

    protected void initFont(List<Pair> fonts) {
        try {
            for (Pair fontData : fonts) {
                File fontFile = (File) fontData.getFirstElement();
                FontType fontType = (FontType) fontData.getSecondElement();

                Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                switch (fontType) {
                    case BOLD: 
                        boldFont = font;
                        break;
                    case EXTRA_BOLD: 
                        extraBoldFont = font;
                        break;
                    case SEMI_BOLD: 
                        semiBoldFont = font;
                        break;
                    case MEDIUM: 
                        mediumFont = font;
                        break;
                    case REGULAR: 
                        regularFont = font;
                        break;
                    case LIGHT: 
                        lightFont = font;
                        break; 
                }
                GRAPHICS_ENVIRONMENT.registerFont(font);
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void initSize(List<Pair> sizes) {
        for (Pair sizeData: sizes) {
            float size = (float) sizeData.getFirstElement();
            FontSize fontSize = (FontSize) sizeData.getSecondElement();

            switch (fontSize) {
                case LARGE: 
                    largeSize = size;
                    break;
                case MEDIUM: 
                    mediumSize = size;
                    break;
                case SMALL: 
                    smallSize = size;
                    break;
            }
        }
    }

    public Font getBoldFont(float fontSize) {
        return boldFont.deriveFont(fontSize);
    }

    public Font getBoldFont(FontSize fontSize) {
        switch (fontSize) {
            case LARGE: 
                return boldFont.deriveFont(largeSize);
            case MEDIUM: 
                return boldFont.deriveFont(mediumSize);
            case SMALL:
                return boldFont.deriveFont(smallSize);
        }

        return boldFont;
    }

    public Font getExtraBoldFont(float fontSize) {
        return extraBoldFont.deriveFont(fontSize);
    }

    public Font getExtraBoldFont(FontSize fontSize) {
        switch (fontSize) {
            case LARGE: 
                return extraBoldFont.deriveFont(largeSize);
            case MEDIUM: 
                return extraBoldFont.deriveFont(mediumSize);
            case SMALL:
                return extraBoldFont.deriveFont(smallSize);
        }

        return extraBoldFont;
    }

    public Font getSemiBoldFont(float fontSize) {
        return semiBoldFont.deriveFont(fontSize);
    }

    public Font getSemiBoldFont(FontSize fontSize) {
        switch (fontSize) {
            case LARGE: 
                return semiBoldFont.deriveFont(largeSize);
            case MEDIUM: 
                return semiBoldFont.deriveFont(mediumSize);
            case SMALL:
                return semiBoldFont.deriveFont(smallSize);
        }

        return semiBoldFont;
    }

    public Font getMediumFont(float fontSize) {
        return mediumFont.deriveFont(fontSize);
    }

    public Font getMediumFont(FontSize fontSize) {
        switch (fontSize) {
            case LARGE: 
                return mediumFont.deriveFont(largeSize);
            case MEDIUM: 
                return mediumFont.deriveFont(mediumSize);
            case SMALL:
                return mediumFont.deriveFont(smallSize);
        }

        return mediumFont;
    }

    public Font getRegularFont(float fontSize) {
        return regularFont.deriveFont(fontSize);
    }

    public Font getRegularFont(FontSize fontSize) {
        switch (fontSize) {
            case LARGE: 
                return regularFont.deriveFont(largeSize);
            case MEDIUM: 
                return regularFont.deriveFont(mediumSize);
            case SMALL:
                return regularFont.deriveFont(smallSize);
        }

        return regularFont;
    }

    public Font getLightFont(float fontSize) {
        return lightFont.deriveFont(fontSize);
    }

    public Font getLightFont(FontSize fontSize) {
        switch (fontSize) {
            case LARGE: 
                return lightFont.deriveFont(largeSize);
            case MEDIUM: 
                return lightFont.deriveFont(mediumSize);
            case SMALL:
                return lightFont.deriveFont(smallSize);
        }

        return lightFont;
    }
}

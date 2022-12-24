package com.canuc80k.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.canuc80k.datastructure.Pair;

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

    public Font getExtendedFont(FontType type, FontSize size) {
        Font font = regularFont;
        switch (type) {
            case BOLD: 
                font = boldFont;
                break;
            case EXTRA_BOLD: 
                font = extraBoldFont;
                break;
            case SEMI_BOLD: 
                font = semiBoldFont;
                break;
            case MEDIUM: 
                font = mediumFont;
                break;
            case REGULAR: 
                font = regularFont;
                break;
            case LIGHT: 
                font = lightFont;
                break; 
        }
        switch (size) {
            case LARGE: 
                return font.deriveFont(largeSize);
            case MEDIUM: 
                return font.deriveFont(mediumSize);
            case SMALL:
                return font.deriveFont(smallSize);
        }
        return font;
    }

    public Font getExtendedFont(FontType type, float size) {
        Font font = regularFont;
        switch (type) {
            case BOLD: 
                font = boldFont;
                break;
            case EXTRA_BOLD: 
                font = extraBoldFont;
                break;
            case SEMI_BOLD: 
                font = semiBoldFont;
                break;
            case MEDIUM: 
                font = mediumFont;
                break;
            case REGULAR: 
                font = regularFont;
                break;
            case LIGHT: 
                font = lightFont;
                break; 
        }

        return font.deriveFont(size);
    }
}

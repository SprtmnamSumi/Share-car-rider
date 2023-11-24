package cz.muni.fi.pv168.project.util;

import java.awt.Color;

public class ConversionUtils {
    private final static int DIM_COLOR = 150;

    public static Color getDimColor(int rgb) {
        Color brightColor = new Color(rgb);
        return new Color(brightColor.getRed(), brightColor.getGreen(), brightColor.getBlue(), DIM_COLOR);
    }
}

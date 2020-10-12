package com.c0destudy.sokoban.misc;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Resource
{
    public static final String PATH_RESOURCES_IMAGE = "src/resources/skins/%s/%s.png";
    public static final String PATH_RESOURCES_FONT  = "src/resources/fonts/%s.ttf";

    public static final String PATH_LEVEL           = "src/resources/levels/%s.txt";
    public static final String PATH_LEVEL_PAUSE     = "data/pause.dat";
    public static final String PATH_LEVEL_RECORDING = "data/recordings/%s.dat";

    public static Image getImageFromResource(final String skinName, final String imageName) {
        final String    path = String.format(Resource.PATH_RESOURCES_IMAGE, skinName, imageName);
        final ImageIcon icon = new ImageIcon(path);
        return icon.getImage();
    }

    public static void loadFontFromResource(final String name) {
        try {
            final String path = String.format(Resource.PATH_RESOURCES_FONT, name);
            final File file = new File(path);
            final Font   font = Font.createFont(Font.TRUETYPE_FONT, file);
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
}
package com.c0destudy.sokoban.misc;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Resource
{
    public static final String PATH_SKIN_ROOT         = "src/resources/skins/";
    public static final String PATH_FONT_ROOT         = "src/resources/fonts/";

    public static final String PATH_LEVEL             = "src/resources/levels/%s.txt";
    public static final String PATH_LEVEL_ROOT        = "src/resources/levels";
    public static final String PATH_LEVEL_PAUSE       = "data/pause.dat";
    public static final String PATH_LEVEL_BEST_SCORES = "data/bestscores.txt";
  
    public static final String PATH_RECORDING_ROOT    = "data/recordings";
    public static final String PATH_RECORDING_FILE    = "data/recordings/%s (%d pts) (%d moves).dat";
    
    public static final String PATH_SOUND_BACKGROUND  = "src/resources/sounds/game.wav";
    public static final String PATH_SOUND_PLAYER_MOVE = "src/resources/sounds/move.wav";

    public static void loadFontFromResource(final String name) {
        try {
            final String path = Resource.PATH_FONT_ROOT + name + ".ttf";
            final File   file = new File(path);
            final Font   font = Font.createFont(Font.TRUETYPE_FONT, file);
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public static String getSkinResourcePath(final String skinName, final String fileName)  {
        return Resource.PATH_SKIN_ROOT + skinName + "/" + fileName;
    }

    public static Image getSkinImage(final String skinName, final String imageName) {
        if (imageName == null || "".equals(imageName)) return null;
        final String filePath = getSkinResourcePath(skinName, imageName);
        final File   file     = new File(filePath);
        if (!file.exists()) return null;
        return (new ImageIcon(filePath)).getImage();
    }

    public static Font getFont(final String name, final boolean isBold, final int size) {
        return new Font(name, isBold ? Font.BOLD : Font.PLAIN, size);
    }

}

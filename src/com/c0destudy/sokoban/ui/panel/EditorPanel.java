package com.c0destudy.sokoban.ui.panel;

import com.c0destudy.sokoban.resource.Resource;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class EditorPanel extends BasePanel
{
    public EditorPanel(final ActionListener listener) {
        super(listener);

        final String[]             levels   = Resource.getLevelList();
        final ArrayList<Component> levelBox = new ArrayList<>();
        levelBox.add(makeButton("CREATE NEW", 350, 30, true));
        levelBox.add(makeVerticalSpace(20));
        for (final String level : levels) {
            levelBox.add(makeButton(level, 350, 30, true));
            levelBox.add(makeVerticalSpace(10));
        }

        Arrays.asList(
            makeVerticalSpace(50),
            makeTitleLabel("E D I T O R"),
            makeVerticalSpace(40),
            makeScroll(450, 240, false, true, levelBox),
            makeVerticalSpace(20),
            makeLargeButton("Back", 450, 45, true)
        ).forEach(this::add);
    }
}

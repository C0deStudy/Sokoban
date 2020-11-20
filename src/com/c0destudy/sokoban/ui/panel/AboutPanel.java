package com.c0destudy.sokoban.ui.panel;

import java.awt.event.ActionListener;
import java.util.Arrays;

import static com.c0destudy.sokoban.ui.helper.MakeComponent.makeVSpace;

public class AboutPanel extends BasePanel
{
    public AboutPanel(final ActionListener listener) {
        super(listener);

        Arrays.asList(
            makeVSpace(50),
            makeTitleLabel("A B O U T"),
            makeVSpace(40),
            makeTransLabel("Sokoban is a puzzle game in which the player"),
            makeTransLabel("pushes crates or boxes around in a warehouse,"),
            makeTransLabel("trying to get them to storage locations."),
            makeTransLabel("Players can only push boxes forward,"),
            makeTransLabel("and can only move them one at a time."),
            makeTransLabel("Also, players cannot cross the box and cannot pull the box."),
            makeVSpace(20),
            makeTransLabel("Think carefully and move the box to destination!"),
            makeTransLabel("The less you move, the higher your score!"),
            makeVSpace(85),
            makeLargeButton("Back", 450, 45, true)
        ).forEach(this::add);
    }

}

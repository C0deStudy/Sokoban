package com.c0destudy.sokoban.ui.frame;

import com.c0destudy.sokoban.level.Level;
import com.c0destudy.sokoban.level.LevelManager;
import com.c0destudy.sokoban.resource.Skin;
import com.c0destudy.sokoban.ui.panel.BoardPanel;
import com.c0destudy.sokoban.ui.panel.EditorControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditorFrame extends JFrame
{
    private final Level              level;
    private final BoardPanel         boardPanel;
    private final EditorControlPanel controlPanel;

    public EditorFrame(final Level level) {
        super();
        this.level = level;
        level.resetAndResize(Math.max(level.getWidth(), 15), Math.max(level.getHeight(), 15));
        this.boardPanel   = new BoardPanel(level);
        this.controlPanel = new EditorControlPanel(new ControlActionListener());
        boardPanel.setEditable(true);
        boardPanel.setBoundaryVisible(true);
        controlPanel.setLevelName(level.getName());
        controlPanel.setLevelDifficulty(level.getDifficulty());

        setTitle("Sokoban Level Editor - " + level.getName());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new TWindowAdapter());
        initUI();
    }

    private void initUI() {
        // Layout
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(Skin.getCurrentSkin().getColor(Skin.COLORS.Background));
        panel.add(boardPanel);
        panel.add(controlPanel);
        boardPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        controlPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        add(panel);
        pack();

        // Size
        int width  = boardPanel.getWidth() + controlPanel.getWidth();
        int height = Math.max(boardPanel.getHeight(), controlPanel.getHeight());
        panel.setPreferredSize(new Dimension(width, height));
        setSize(panel.getSize());    // 크기 설정
        pack();                      // 크기 맞추기
        setLocationRelativeTo(null); // 화면 중앙으로 이동
    }

    private void closeUI() {
        FrameManager.showMainFrame();
        dispose();
    }

    private boolean saveLevel() {
        String error = null;
        if (level.getPlayers().size() == 0) {
            error = "At least one player is required.";
        } else if (level.getBaggages().size() == 0) {
            error = "At least one baggage is required.";
        } else if (level.getGoals().size() == 0) {
            error = "At least one goal is required.";
        } else if (level.getBaggages().size() > level.getGoals().size()) {
            error = "Too few goals. Please add more.";
        }
        if (error != null) {
            JOptionPane.showMessageDialog(null, error, getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
        }

        level.setName(controlPanel.getLevelName());
        level.setDifficulty(controlPanel.getLevelDifficulty());
        LevelManager.saveLevelToTextFile(level);
        return true;
    }

    private class ControlActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JButton button = (JButton) e.getSource();
            final String  text   = button.getText();
            switch (text) {
                case "Cancel":
                    closeUI();
                    break;
                case "Save":
                    if (saveLevel()) {
                        closeUI();
                    }
                    break;
                default:
                    boardPanel.setTileBrush(text);
                    break;
            }
        }
    }

    private class TWindowAdapter extends WindowAdapter
    {
        @Override
        public void windowClosing(final WindowEvent windowEvent) {
            closeUI();
        }
    }
}
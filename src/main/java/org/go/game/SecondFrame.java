package org.go.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondFrame extends JFrame {
    public static int gameMode = -1; // 0 - 2 player game, 1 - game with bot
    public static int boardSize = 19; // possible sizes - 19, 13, 9
    public static boolean startGame = false;
    public SecondFrame() {
        //size
        setSize(700, 600);

        //title
        setTitle("GO");

        //main components panel
        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(new BoxLayout(componentsPanel, BoxLayout.PAGE_AXIS));

        //game mode buttons
        JButton button2PlayerGame = new JButton("2 PLAYER GAME");

        JButton buttonGameWithBot = new JButton("GAME WITH BOT");
        button2PlayerGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMode = 0;
            }
        });
        buttonGameWithBot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMode = 1;
            }
        });

        //game mode buttons panel
        JPanel modeButtonsPanel = new JPanel();
        modeButtonsPanel.setLayout(new BoxLayout(modeButtonsPanel, BoxLayout.X_AXIS));

        //adding game mode buttons to panel
        modeButtonsPanel.add(button2PlayerGame);
        modeButtonsPanel.add(Box.createHorizontalStrut(10));
        modeButtonsPanel.add(buttonGameWithBot);

        //board size buttons
        JButton button19 = new JButton("19 X 19");
        JButton button13 = new JButton("13 X 13");
        JButton button9 = new JButton("9 X 9");

        button19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardSize = 19;
            }
        });

        button13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardSize = 13;
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardSize = 9;
            }
        });

        //board size buttons panel
        JPanel boardSizeButtonsPanel = new JPanel();
        boardSizeButtonsPanel.setLayout(new BoxLayout(boardSizeButtonsPanel, BoxLayout.X_AXIS));

        //adding board size buttons to panel
        boardSizeButtonsPanel.add(button19);
        boardSizeButtonsPanel.add(Box.createHorizontalStrut(10));
        boardSizeButtonsPanel.add(button13);
        boardSizeButtonsPanel.add(Box.createHorizontalStrut(10));
        boardSizeButtonsPanel.add(button9);

        //button start
        JButton startButton = new JButton("start game");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameFrame();
                //startGame = true;
            }
        });

        //board size buttons panel
        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setLayout(new BoxLayout(startButtonPanel, BoxLayout.X_AXIS));

        //adding board size buttons to panel
        startButtonPanel.add(startButton);

        componentsPanel.add(Box.createVerticalGlue());
        componentsPanel.add(modeButtonsPanel);
        componentsPanel.add(Box.createVerticalStrut(10));
        componentsPanel.add(boardSizeButtonsPanel);
        componentsPanel.add(Box.createVerticalStrut(10));
        componentsPanel.add(startButtonPanel);
        componentsPanel.add(Box.createVerticalGlue());

        // Center the panel on the frame
        setLayout(new BorderLayout());
        add(componentsPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public static int getGameMode() {
        return gameMode;
    }

    public static int getBoardSize() {
        return boardSize;
    }

    public static boolean getStartGame() {
        return startGame;
    }
}
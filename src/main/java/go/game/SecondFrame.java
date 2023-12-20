package go.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondFrame extends JFrame {
    public static int gameMode = -1; // 0 - 2 player game, 1 - game with bot
    public static int boardSize = 19; // possible sizes - 19, 13, 9
    private boolean setBoardSize = false;
    public boolean setGameMode = false;
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
                setGameMode = true;
            }
        });
        buttonGameWithBot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMode = 1;
                setGameMode = true;
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
                setBoardSize = true;
            }
        });

        button13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardSize = 13;
                setBoardSize = true;
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardSize = 9;
                setBoardSize = true;
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

        JLabel errorLabel = new JLabel("");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (setBoardSize && setGameMode) {
                    setStartGame(true);
                    errorLabel.setText("Waiting for players to join.");
                } else {
                    errorLabel.setText("You have to choose game mode and board size!");
                }
            }
        });

        //start button panel
        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setLayout(new BoxLayout(startButtonPanel, BoxLayout.X_AXIS));

        //adding start button to panel
        startButtonPanel.add(startButton);

        //error label panel
        JPanel errorLabelPanel = new JPanel();
        errorLabelPanel.setLayout(new BoxLayout(errorLabelPanel, BoxLayout.X_AXIS));

        //adding start button to panel
        errorLabelPanel.add(errorLabel);

        componentsPanel.add(Box.createVerticalGlue());
        componentsPanel.add(modeButtonsPanel);
        componentsPanel.add(Box.createVerticalStrut(10));
        componentsPanel.add(boardSizeButtonsPanel);
        componentsPanel.add(Box.createVerticalStrut(10));
        componentsPanel.add(startButtonPanel);
        componentsPanel.add(Box.createVerticalStrut(10));
        componentsPanel.add(errorLabelPanel);
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

    public static void setStartGame(boolean value) {
        startGame = value;
    }

    public static boolean getStartGame() {
        return startGame;
    }
}
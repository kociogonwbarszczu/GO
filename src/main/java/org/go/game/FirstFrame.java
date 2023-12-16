package org.go.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstFrame extends JFrame {
    public FirstFrame() {
        //size
        setSize(700, 600);

        //title
        setTitle("org.go.game.ClientServer.GO");

        //main components panel
        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(new BoxLayout(componentsPanel, BoxLayout.PAGE_AXIS));

        //label
        JLabel titleLabel = new JLabel("org.go.game.ClientServer.GO");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //buttons
        JButton buttonNewGame = new JButton("NEW GAME");
        buttonNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SecondFrame();
            }
        });

        JButton buttonLoadGame = new JButton("LOAD GAME");
        buttonLoadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonLoadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoadGameFrame();
            }
        });

        componentsPanel.add(Box.createVerticalGlue());
        componentsPanel.add(titleLabel);
        componentsPanel.add(Box.createVerticalStrut(20));
        componentsPanel.add(buttonNewGame);
        componentsPanel.add(Box.createVerticalStrut(10));
        componentsPanel.add(buttonLoadGame);
        componentsPanel.add(Box.createVerticalGlue());

        // Center the panel on the frame
        setLayout(new BorderLayout());
        add(componentsPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
}

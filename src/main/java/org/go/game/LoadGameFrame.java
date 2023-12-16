package org.go.game;

import javax.swing.*;
import java.awt.*;

public class LoadGameFrame extends JFrame {
    public LoadGameFrame() {
        //size
        setSize(700, 600);

        //title
        setTitle("org.go.game.ClientServer.GO");

        //main components panel
        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(new BoxLayout(componentsPanel, BoxLayout.PAGE_AXIS));

        //label
        JLabel titleLabel = new JLabel("Something will be here soon :)");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        componentsPanel.add(Box.createVerticalGlue());
        componentsPanel.add(titleLabel);
        componentsPanel.add(Box.createVerticalGlue());

        // Center the panel on the frame
        setLayout(new BorderLayout());
        add(componentsPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
}

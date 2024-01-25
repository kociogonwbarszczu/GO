package go.game.frames;

import go.game.frames.style.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AfterSkipFrame extends JFrame {

    public AfterSkipFrame() {

        //size
        setSize(500, 400);

        //title
        setTitle("GO");

        //main components panel
        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(new BoxLayout(componentsPanel, BoxLayout.PAGE_AXIS));

        //resume game button
        MyButton resumeGameButton = new MyButton("resume game");
        resumeGameButton.setFont(new Font(resumeGameButton.getFont().getName(), Font.BOLD, 15));
        resumeGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //end game button
        MyButton endGameButton = new MyButton("end game");
        endGameButton.setFont(new Font(endGameButton.getFont().getName(), Font.BOLD, 15));
        endGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        componentsPanel.add(Box.createVerticalGlue());
        componentsPanel.add(resumeGameButton);
        componentsPanel.add(Box.createVerticalStrut(20));
        componentsPanel.add(endGameButton);
        componentsPanel.add(Box.createVerticalGlue());

        // Center the panel on the frame
        setLayout(new BorderLayout());
        add(componentsPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
        setResizable(false);
    }

}

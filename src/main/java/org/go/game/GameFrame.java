package org.go.game;

import org.go.game.drawing.DrawBoard;
import org.go.game.drawing.DrawStone;
import org.go.game.drawing.DrawableElement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {

    private List<DrawableElement> elements;

    public GameFrame() {
        // size
        setSize(700, 600);

        // title
        setTitle("GO");

        // create elements list
        elements = new ArrayList<>();

        // add elements to the list
        elements.add(new DrawBoard());
        //elements.add(new DrawStone(3, 3, Color.BLACK));
        //elements.add(new DrawStone(10, 10, Color.WHITE));
        //elements.add(new DrawStone(0, 0, Color.BLACK));

        // set content pane
        setContentPane(new DrawingPanel());

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (DrawableElement element : elements) {
                element.draw(g);
            }
        }
    }
}


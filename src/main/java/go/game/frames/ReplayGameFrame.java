package go.game.frames;

import go.game.Database.SQLSaveGame;
import go.game.drawing.Board;
import go.game.drawing.Stone;
import go.game.drawing.DrawableElement;
import go.game.frames.style.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ReplayGameFrame extends JFrame {

    private static Map<Point, DrawableElement> elements;
    private static final int boardSize = 19;
    private final int cellSize = 30;
    private static int gameId;


    public ReplayGameFrame(int gameNumber) {
        gameId = gameNumber;

        // size
        setSize(700, 607);

        SQLSaveGame sqlSaveGame = new SQLSaveGame();
        gameId = sqlSaveGame.getIDGame();

        // title
        setTitle("GO - replay game " + gameId);

        // create elements map
        elements = new HashMap<>();

        // set content pane
        setLayout(new BorderLayout());

        // drawing panel with board
        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        // game id label
        JLabel gameIdLabel = new JLabel("GAME ID: " + gameId);
        gameIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // next move button
        MyButton nextButton = new MyButton("next move");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(nextButton);
        buttonPanel.add(Box.createVerticalStrut(10));


        //main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        add(mainPanel, BorderLayout.EAST);

        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(gameIdLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(Box.createVerticalStrut(5));

        addStone(2, 3, Color.BLACK);
        addStone(15, 10, Color.WHITE);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // draw the board
            Board board = new Board();
            board.draw(g);

            // draw stones
            for (Map.Entry<Point, DrawableElement> entry : elements.entrySet()) {
                Point position = entry.getKey();
                DrawableElement element = entry.getValue();

                int x = position.x * 30; // assuming cell size is 30
                int y = position.y * 30;

                g.translate(x, y);
                element.draw(g);
                g.translate(-x, -y);
            }
        }
    }

    private void addStone(int x, int y, Color color) {
        elements.put(new Point(x, y), Stone.addStone(color));
    }
    private void removeStone(int x, int y) {
        elements.remove(new Point(x, y));
    }

}

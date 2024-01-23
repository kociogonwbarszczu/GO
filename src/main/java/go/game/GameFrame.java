package go.game;

import go.game.ClientServer.Client;
import go.game.drawing.Board;
import go.game.drawing.Stone;
import go.game.drawing.DrawableElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class GameFrame extends JFrame {

    private Map<Point, DrawableElement> elements;
    private JTextPane textPane;
    private int boardSize = 19;
    private int cellSize = Board.getCellSize();
    private Color playerColor;
    public int rowSelected = -1;
    public int columnSelected = -1;
    private static boolean sendMove = false;

    private static char[][] currentBoard = new char[19][19];
    private static char[][] serverBoard = new char[19][19];

    public GameFrame(Color color, Client client) {
        playerColor = color;
        // size
        setSize(700, 600);

        // title
        if(playerColor == Color.BLACK){
            setTitle("GO - player 1");
        }
        else{
            setTitle("GO - player 2");
        }

        // create elements map
        elements = new HashMap<>();

        // set content pane
        setLayout(new BorderLayout());

        // Create the DrawingPanel and add it to the center
        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        // Create the JTextPane and add it to the right side
        textPane = new JTextPane();
        textPane.setEditable(false);

        // Set up a JScrollPane for the JTextPane (optional)
        JScrollPane scrollPane = new JScrollPane(textPane);
        add(scrollPane, BorderLayout.EAST);

        // Add a mouse listener to the drawing panel
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Calculate the position of the click in terms of the grid
                int x = e.getX() / cellSize;
                int y = e.getY() / cellSize;

                if((x < boardSize) && (y < boardSize)){
                    // Add a stone at the clicked position
                    elements.put(new Point(x, y), Stone.addStone(playerColor));

                    //adding coordinates to client
                    setRowSelected(x);
                    setColumnSelected(y);
                    setMove(true);
                    client.updateMove(rowSelected, columnSelected);

                    // Aktualizacja tekstu w JTextPane
                    String currentText = textPane.getText();
                    String newText = currentText + String.format("Stone added at coordinates (%d, %d)\n", x, y);
                    textPane.setText(newText);

                    // Odświeżenie widoku
                    drawingPanel.repaint();

                    //setMove(false);
                    setRowSelected(-1);
                    setColumnSelected(-1);
                }
            }
        });

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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

    public void setMove(boolean b) {
        sendMove = b;
    }

    public boolean getMove() {
        return sendMove;
    }

    private void setRowSelected(int x) {
        rowSelected = x;
    }

    private void setColumnSelected(int y) {
        columnSelected = y;
    }
}

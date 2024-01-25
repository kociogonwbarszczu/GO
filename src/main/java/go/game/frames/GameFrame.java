package go.game.frames;

import go.game.ClientServer.Client;
import go.game.logic.DefaultLogicStrategy;
import go.game.logic.Logic;
import go.game.drawing.Board;
import go.game.drawing.Stone;
import go.game.drawing.DrawableElement;
import go.game.frames.style.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class GameFrame extends JFrame {

    private static Map<Point, DrawableElement> elements;
    private static JTextPane text;
    private static int boardSize = 19;
    private int cellSize = 30;
    private Color playerColor;
    public int rowSelected = -1;
    public int columnSelected = -1;
    private static boolean sendMove = false;
    private static boolean yourTurn;
    private static boolean skip = false;
    private int gameId = 1;
    static Logic logic = new Logic(new DefaultLogicStrategy());


    public GameFrame(Color color, Client client) {
        playerColor = color;
        // size
        setSize(815, 607);

        // title
        if(playerColor == Color.BLACK){
            setTitle("GO - player 1");
            yourTurn = true;
        }
        else{
            setTitle("GO - player 2");
            yourTurn = false;
        }

        // create elements map
        elements = new HashMap<>();

        // set content pane
        setLayout(new BorderLayout());

        // Create the DrawingPanel and add it to the center
        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        add(mainPanel, BorderLayout.EAST);

        JLabel gameIdLabel = new JLabel("GAME ID: " + gameId);
        gameIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        MyButton skipButton = new MyButton("skip your move");
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRowSelected(-1);
                setColumnSelected(-1);
                setMove(true);
                client.updateMove(rowSelected, columnSelected);
                yourTurn = false;
                String currentText = text.getText();
                String newText = currentText + "You skipped move.\nOpponent's turn.\n\n";
                text.setText(newText);
            }
        });

        skipButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        MyButton surrenderButton = new MyButton("surrender");
        surrenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.surrenderPlayer(playerColor);
                new GameOverFrame(playerColor);
                new AfterSkipFrame();
            }
        });

        surrenderButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        text = new JTextPane();
        text.setText("GO game started                                  \n");        //do not touch the spaces
        text.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(gameIdLabel);
        //buttonPanel.add(Box.createVerticalStrut(10));
        //buttonPanel.add(gameIdLabel);
        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(skipButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(surrenderButton);


        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        textPanel.add(text);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(textPanel);
        // Create the JTextPane and add it to the right side

        // Set up a JScrollPane for the JTextPane (optional)
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textPanel.add(scrollPane, BorderLayout.EAST);

        // Add a mouse listener to the drawing panel
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Calculate the position of the click in terms of the grid
                int x = e.getX() / cellSize;
                int y = e.getY() / cellSize;

                if((x < boardSize) && (y < boardSize) && Logic.ifAlreadyOccupied(x, y) && yourTurn){
                    // Add a stone at the clicked position
                    elements.put(new Point(x, y), Stone.addStone(playerColor));
                    logic.updateBoard(x, y, color);

                    //adding coordinates to client
                    setRowSelected(x);
                    setColumnSelected(y);
                    setMove(true);
                    client.updateMove(rowSelected, columnSelected);
                    yourTurn = false;

                    // Aktualizacja tekstu w JTextPane
                    String currentText = text.getText();
                    String newText = currentText + String.format("Stone added at coordinates (%d, %d)\nOpponent's turn.\n\n", x, y) + String.format("Breath: %d", logic.countBreath(x, y));
                    text.setText(newText);

                    // Odświeżenie widoku
                    drawingPanel.repaint();

                    //setMove(false);
                    setRowSelected(-1);
                    setColumnSelected(-1);
                }
            }
        });

        int xPosition = 0;
        if(playerColor == Color.BLACK){
            xPosition = 0;
        }
        else {
            xPosition = 700;
        }

        int yPosition = 100;
        setLocation(xPosition, yPosition);
        //setLocationRelativeTo(null);
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

    public static void setYourTurn() {
        yourTurn = true;
        String currentText = text.getText();
        String newText = currentText + "Opponent skipped move.\nYour turn\n\n";
        text.setText(newText);
    }

    public static void addOpponentsMove(int x, int y, Color playerColor) {
        elements.put(new Point(x, y), Stone.addStone(playerColor));
        logic.updateBoard(x, y, playerColor);
        yourTurn = true;
        String currentText = text.getText();
        String newText = currentText + String.format("Stone added at coordinates (%d, %d).\nYour turn. \n\n", x, y);

        text.setText(newText);
    }
}

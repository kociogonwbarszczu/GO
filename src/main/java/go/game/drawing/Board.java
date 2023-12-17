package go.game.drawing;

import go.game.SecondFrame;

import java.awt.*;

public class Board implements DrawableElement {

    private static int boardSize = SecondFrame.getBoardSize();
    private static int cellSize = 30;
    @Override
    public void draw(Graphics g) {
        // Draw a border around the entire board
        Color borderColor = new Color(10, 40, 25, 255);
        g.setColor(borderColor);
        g.fillRect(0, 0, boardSize*cellSize, boardSize*cellSize); // assuming board size is 19 and cell size is 30

        Color boardColor = new Color(175, 235, 210, 255);

        for (int i = 0; i < boardSize - 1; i++) {
            for (int j = 0; j < boardSize - 1; j++) {
                int x = cellSize/2 + i * cellSize;
                int y = cellSize/2 + j * cellSize;

                g.setColor(boardColor);

                g.fillRect(x, y, cellSize, cellSize);

                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
    }

    public static int getCellSize(){
        return cellSize;
    }
}


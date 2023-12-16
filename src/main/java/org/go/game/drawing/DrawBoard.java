package org.go.game.drawing;

import java.awt.*;

import static org.go.game.SecondFrame.*;

public class DrawBoard implements DrawableElement {
    private static final int boardSize = getBoardSize();
    private static final int cellSize = 30;

    @Override
    public void draw(Graphics g) {
        // Draw a border around the entire board
        Color borderColor = new Color(10, 40, 25, 255);
        g.setColor(borderColor);
        g.fillRect(0, 0, (boardSize - 1) * cellSize + 30, (boardSize - 1) * cellSize + 30);

        Color boardColor = new Color(175, 235, 210, 255);

        for (int i = 0; i < boardSize - 1; i++) {
            for (int j = 0; j < boardSize - 1; j++) {
                int x = 15 + i * cellSize;
                int y = 15 + j * cellSize;

                g.setColor(boardColor);

                g.fillRect(x, y, cellSize, cellSize);

                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
    }
}

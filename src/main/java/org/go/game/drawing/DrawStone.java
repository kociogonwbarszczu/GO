package org.go.game.drawing;

import java.awt.*;

public class DrawStone implements DrawableElement {

    private int row, col;
    private int cellSize = 30;
    private Color color;

    public DrawStone(int row, int col, Color color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        int x = row * cellSize;
        int y = col * cellSize;

        g.setColor(color);
        g.fillOval(x, y, cellSize, cellSize);
    }
}

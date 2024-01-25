package go.game.ClientServer;

import java.awt.*;

public interface LogicStrategy {
    int countBreath(int[][] board, int column, int row);
    boolean ifAlreadyOccupied(int[][] board, int x, int y);
    void updateBoard(int[][] board, int x, int y, Color color);
    char getElement(int[][] board, int x, int y);
}

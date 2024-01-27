package go.game.logic;


import java.awt.*;

public class Logic {
    private static final int boardSize = 19;
    public static int[][] board = new int[boardSize][boardSize];
    private static LogicStrategy logicStrategy;

    public Logic(LogicStrategy logicStrategy) {
        this.logicStrategy = logicStrategy;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++){
                board[i][j] = ' ';
            }
        }
    }

    // Metoda do ustawiania strategii
    public void setLogicStrategy(LogicStrategy logicStrategy) {
        this.logicStrategy = logicStrategy;
    }

    public int countBreath(int column, int row) {
        return logicStrategy.countBreath(board, column, row);
    }

    public int countBreathHypothetical(int column, int row, Color color) {
        return logicStrategy.countBreathHypothetical(board, column, row, color);
    }

    public void removeStonesWithoutBreath() {
        logicStrategy.removeStonesWithoutBreath(board);
    }

    public static boolean ifAlreadyOccupied(int x, int y) {
        return logicStrategy.ifAlreadyOccupied(board, x, y);
    }

    public static void updateBoard(int x, int y, Color color) {
        logicStrategy.updateBoard(board, x, y, color);
    }

    public static void counter() {
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                // countBreath(j, i);
            }
        }
    }

    public char getElement(int x, int y) {
        return (char) logicStrategy.getElement(board, x, y);
    }

}

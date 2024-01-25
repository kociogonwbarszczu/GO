package go.game.ClientServer;

import java.awt.*;

public class DefaultLogicStrategy implements  LogicStrategy{
    @Override
    public int countBreath(int[][] board, int column, int row) {
        char element = getElement(board, column, row);
        int breath = 0;

        // górne pole
        breath += checkBreathForStone(board, column, row - 1, element);

        // dolne pole
        breath += checkBreathForStone(board, column, row + 1, element);

        // lewe pole
        breath += checkBreathForStone(board, column - 1, row, element);

        // prawe pole
        breath += checkBreathForStone(board, column + 1, row, element);

        return breath;
    }

    @Override
    public boolean ifAlreadyOccupied(int[][] board, int x, int y) {
        System.out.println(board[x][y]);
        return !(board[x][y] == 'W' || board[x][y] == 'B');
    }

    @Override
    public void updateBoard(int[][] board, int x, int y, Color color) {
        if (color == Color.BLACK) board[x][y] = 'B';
        else board[x][y] = 'W';
    }

    @Override
    public char getElement(int[][] board, int x, int y) {
        return (char) board[x][y];
    }

    private int checkBreathForStone(int[][] board, int column, int row, char color) {
        int boardSize = board.length;
        if (column < 0 || column >= boardSize || row < 0 || row >= boardSize) return 0;

        char checkedElement = getElement(board, column, row);

        if (checkedElement == ' ') return 1;
        else if (checkedElement == color) return countBreath(board, column, row);
        else return 0;
    }
}

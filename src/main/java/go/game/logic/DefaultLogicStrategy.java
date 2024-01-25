package go.game.logic;

import java.awt.*;
import java.util.Stack;

public class DefaultLogicStrategy implements  LogicStrategy{
    @Override
    public int countBreath(int[][] board, int column, int row) {
        char element = getElement(board, column, row);
        int breath = 0;
        boolean[][] visited = new boolean[board.length][board[0].length]; // tablica do śledzenia odwiedzonych pól
        Stack<int[]> stack = new Stack<>(); // śledzenie współrzędnych do odwiedzenia
        stack.push(new int[]{column, row}); // dodanie na stos początkowych współrzędnych

        // dopóki stos nie jest pusty
        while (!stack.isEmpty()) {
            int[] currentPosition = stack.pop(); // wierzchołek stosu
            int currentColumn = currentPosition[0];
            int currentRow = currentPosition[1];

            if (currentColumn < 0 || currentColumn >= 19 || currentRow < 0 || currentRow >= 19 || visited[currentColumn][currentRow]) {
                continue;
            }

            visited[currentColumn][currentRow] = true;

            char checkedElement = getElement(board, currentColumn, currentRow);

            if (checkedElement == ' ') {
                breath++;
            } else if (checkedElement == element) {
                stack.push(new int[]{currentColumn, currentRow - 1});
                stack.push(new int[]{currentColumn, currentRow + 1});
                stack.push(new int[]{currentColumn - 1, currentRow});
                stack.push(new int[]{currentColumn + 1, currentRow});
            }
        }

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
}

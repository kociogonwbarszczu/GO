package go.game.ClientServer;


import java.awt.*;
import java.util.Arrays;

public class Logic {
    private static final int boardSize = 19;
    public static char[][] board = new char[boardSize][boardSize];

    public static boolean ifAlreadyOccupied(int x, int y) {
        System.out.println(board[x][y]);
        return !(board[x][y] == 'W' || board[x][y] == 'B');
    }

    public static void updateBoard(int x, int y, Color color){
        if (color == Color.BLACK) board[x][y] = 'B';
        else board[x][y] = 'W';
    }

    public static boolean countBreath(char[][] board){
        for (int i = 0; i < 19; i++){
            for (int j = 0; j < 19; j++) {

            }
        }
        return false;
    }

}

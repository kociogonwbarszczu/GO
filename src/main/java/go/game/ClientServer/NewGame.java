package go.game.ClientServer;

import go.game.GameFrame;
import go.game.SecondFrame;

import java.io.*;
import java.net.*;
import java.util.Arrays;

class NewGame implements Runnable {
    public static int PLAYER1_WON = 1;
    public static int PLAYER2_WON = 2;
    public static int CONTINUE = 3;

    //poprawa
    private int boardSize = SecondFrame.getBoardSize();
    private char[][] board = new char[boardSize][boardSize];

    private Socket firstPlayer;
    private Socket secondPlayer;


    public NewGame(Socket firstPlayer, Socket secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++){
                board[i][j] = ' ';
            }
        }
    }
    @Override
    public void run() {
        try {
            DataInputStream inputFirstPlayer = new DataInputStream(firstPlayer.getInputStream());
            DataOutputStream outputFirstPlayer = new DataOutputStream(firstPlayer.getOutputStream());
            DataInputStream inputSecondPlayer = new DataInputStream(secondPlayer.getInputStream());
            DataOutputStream outputSecondPlayer = new DataOutputStream(secondPlayer.getOutputStream());
            // starting game
            while (true) {
                int row = inputFirstPlayer.readInt();
                int column = inputFirstPlayer.readInt();
                board[row][column] = 'B';

                outputSecondPlayer.writeInt(CONTINUE);
                sendMove(outputSecondPlayer, row, column);

                row = inputSecondPlayer.readInt();
                column = inputSecondPlayer.readInt();
                board[row][column] = 'W';

                outputFirstPlayer.writeInt(CONTINUE);
                sendMove(outputFirstPlayer, row, column);

                System.out.println(Arrays.deepToString(board));

                //gameFrame1.repaint();
                //gameFrame2.repaint();
            }

        } catch (IOException ex) {
            System.err.println("ex");
        }
    }

    private void sendMove(DataOutputStream out, int row, int column) throws IOException {
        out.writeInt(row);
        out.writeInt(column);
    }
}

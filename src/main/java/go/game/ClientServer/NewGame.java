package go.game.ClientServer;

import go.game.frames.AfterSkipFrame;
import go.game.frames.GameFrame;
import go.game.frames.ResumeGameFrame;

import java.io.*;
import java.net.*;

public class NewGame implements Runnable {
    public static int countSkip = 0;
    private static boolean stop = false;
    private static boolean end = false;
    static int chosenPlayer = 0;

    private static final int boardSize = 19;
    private static final char[][] board = new char[boardSize][boardSize];

    private final Socket firstPlayer;
    private final Socket secondPlayer;

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
            while (!end) {
                int row;
                int column;

                if (!stop) {
                    row = inputFirstPlayer.readInt();
                    column = inputFirstPlayer.readInt();
                    sendMove(outputSecondPlayer, row, column);

                    if (row != -1 && column != -1) {
                        board[row][column] = 'B';
                        countSkip = 0;
                    } else countSkip += 1;
                }
                System.out.println(countSkip);
                if (countSkip == 2) skipTwice();


                if (!stop) {
                    row = inputSecondPlayer.readInt();
                    column = inputSecondPlayer.readInt();
                    sendMove(outputFirstPlayer, row, column);
                    if (row != -1 && column != -1) {
                        board[row][column] = 'W';
                        countSkip = 0;
                    } else countSkip += 1;
                }
                System.out.println(countSkip);
                if (countSkip == 2) skipTwice();

                //System.out.println(Arrays.deepToString(board));
            }

        } catch (IOException ex) {
            System.err.println("ex");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMove(DataOutputStream out, int row, int column) throws IOException {
        out.writeInt(row);
        out.writeInt(column);
    }

    public static void skipTwice() throws InterruptedException {
        countSkip = 0;
        stop = true;
        new AfterSkipFrame();
        GameFrame.setStop(true);
        waitForResumeOrEnd();

        if (ResumeGameFrame.getResume()) {
            chosenPlayer = ResumeGameFrame.getPlayerWhoStarts();
            stop = false;
        } else if (AfterSkipFrame.getEnd()) {
            AfterSkipFrame.setEnd();
            end = true;
        }
    }
    public static int getChosenPlayer() {
        return chosenPlayer;
    }

    private static void waitForResumeOrEnd() throws InterruptedException {
        while (!(ResumeGameFrame.getResume() || AfterSkipFrame.getEnd())) {
            Thread.sleep(100);
        }
    }
}

package go.game.ClientServer;

import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Client implements Runnable {
    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;
    private boolean startGame = false;
    private boolean myTurn = false;
    private static int boardSize = 9;
    public static char[][] board = new char[9][9];

    private int rowSelected;
    private int columnSelected;

    private DataInputStream fromServer;
    private DataOutputStream toServer;

    private boolean continueToPlay = true;
    public char myColor = ' ';
    public char otherColor = ' ';

    public static void main(String[] args) {
        Client display = new Client();
        display.connectToServer();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++){
                board[i][j] = ' ';
            }
        }
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 666);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            int player = fromServer.readInt();
            System.out.println(player);

            if (player == PLAYER1) {
                myColor = 'B';
                otherColor = 'W';
                System.out.println("Your move.");

                myTurn = true;
            } else if (player == PLAYER2) {
                myColor = 'W';
                otherColor = 'B';
                System.out.println("Waiting for the opponent's move.");
            }

            while (continueToPlay) {
                if (myTurn) {
                    waitForMove();
                    sendMove();
                    receiveInfoFromServer();
                    //System.out.println(Arrays.deepToString(board));
                } else {
                    receiveInfoFromServer();
                    //System.out.println(Arrays.deepToString(board));
                    waitForMove();
                    sendMove();
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void waitForMove() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter row (0-" + (boardSize - 1) + "): ");
        rowSelected = scanner.nextInt();

        System.out.println("Enter column (0-" + (boardSize - 1) + "): ");
        columnSelected = scanner.nextInt();
        System.out.println("Waiting for opponent's move.");
        myTurn = false;
    }

    private void sendMove() throws IOException {
        toServer.writeInt(rowSelected);
        toServer.writeInt(columnSelected);
    }

    private void receiveMove() throws IOException {
        int row = fromServer.readInt();
        int column = fromServer.readInt();
        board[row][column] = otherColor;
    }

    private void receiveInfoFromServer() throws IOException {
        receiveMove();
        System.out.println("Your move.");
        myTurn = true;
    }
}

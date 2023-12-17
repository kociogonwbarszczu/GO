package org.go.game.ClientServer;

import org.go.game.SecondFrame;

import java.net.*;
import java.io.*;

public class Client implements Runnable {

    private boolean startGame = false;
    private boolean myTurn = false;
    private static int boardSize = 9;
    public static char[][] board;

    private int rowSelected;
    private int columnSelected;

    private DataInputStream fromServer;
    private DataOutputStream toServer;

    private boolean waiting = true;
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

        display.simulateMoves();
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

            if (player == 1) {
                myColor = 'B';
                otherColor = 'W';

                myTurn = true;
            } else if (player == 2) {
                myColor = 'W';
                otherColor = 'B';
            }

            while (continueToPlay) {
                if (myTurn) {
                    System.out.println("Your move.");
                    waitForMove();
                    sendMove();
                    receiveInfoFromServer();
                } else {
                    System.out.println("Waiting for the opponent's move.");
                    receiveInfoFromServer();
                    waitForMove();
                    sendMove();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void waitForMove() throws InterruptedException {
        while (waiting) {
            Thread.sleep(100);
        }
        waiting = true;
    }

    private void sendMove() throws IOException {
        toServer.writeInt(rowSelected);
        toServer.writeInt(columnSelected);
    }

    private void recieveMove() throws IOException {
        int row = fromServer.readInt();
        int column = fromServer.readInt();
        board[row][column] = otherColor;
    }

    private void receiveInfoFromServer() throws IOException {
        recieveMove();
        myTurn = true;
    }

    private void simulateMoves() {
        // Simulate player 1 making a move
        rowSelected = 0;
        columnSelected = 0;
        waiting = false;
        myTurn = false;

        // Simulate player 2 making a move
        rowSelected = 1;
        columnSelected = 1;
        waiting = false;
        myTurn = false;

        rowSelected = 2;
        columnSelected = 2;
        waiting = false;
        myTurn = false;

        // Simulate player 2 making a move
        rowSelected = 3;
        columnSelected = 3;
        waiting = false;
        myTurn = false;
    }
}

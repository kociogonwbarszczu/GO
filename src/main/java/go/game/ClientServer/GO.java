package go.game.ClientServer;

import go.game.FirstFrame;
import go.game.GameFrame;
import go.game.SecondFrame;

import java.awt.*;
import java.io.*;
import java.net.*;

public class GO {
    public int gameMode = -1;
    public NewGame currentGame;
    public static boolean startGame = false;
    public int boardSize = 19;
    boolean player2Joined = false;

    public static void main(String[] args) {
        new GO();
    }

    public GO() {
        // starting new server
        try (ServerSocket serverSocket = new ServerSocket(666)){
            System.out.println("Starting server on port 666.");

            while (true) {
                FirstFrame firstFrame = new FirstFrame();
                waitForNewOrLoadGame(firstFrame);

                if (firstFrame.getNewGame()) {
                    SecondFrame secondFrame = new SecondFrame();
                    firstFrame.dispose();
                    waitForStartGame();

                    gameMode = SecondFrame.getGameMode();

                    // connect first player
                    System.out.println("Waiting to join player.");
                    Socket firstPlayer = serverSocket.accept();
                    new DataOutputStream(firstPlayer.getOutputStream()).writeInt(1);
                    System.out.println("Player one connected.");

                    // adding second player or bot

                    System.out.println(boardSize);
                    if (gameMode == 0) {
                        System.out.println("Waiting for second player...");
                        Socket secondPlayer = serverSocket.accept();
                        new DataOutputStream(secondPlayer.getOutputStream()).writeInt(2);
                        player2Joined = true;
                        System.out.println("Player second connected.");

                        initializeGame(firstPlayer, secondPlayer);
                        secondFrame.dispose();
                    } else {
                        System.out.println("Bot connected.");
                        initalizeGameWithBot();
                        secondFrame.dispose();
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void waitForNewOrLoadGame(FirstFrame firstFrame) throws InterruptedException {
        while (!(firstFrame.getNewGame() || firstFrame.getLoadGame())){
            Thread.sleep(100);
        }
    }
    private void waitForStartGame() throws InterruptedException {
        System.out.println("Player one is setting mode of games...");

        while (!(SecondFrame.getStartGame())){
            Thread.sleep(100);
        }
        startGame = true;
    }

    private void initializeGame (Socket firstPlayerSocket, Socket secondPlayerSocket) throws InterruptedException {
        while (!(player2Joined)) {
            Thread.sleep(100);
        }

        System.out.println("Both players joined. Starting game.");
        currentGame = new NewGame(firstPlayerSocket, secondPlayerSocket);

        Thread thread = new Thread(currentGame);
        thread.start();
        startGame = false;
    }

    private void initalizeGameWithBot() {
        FirstFrame.startGame = true;
    }
}

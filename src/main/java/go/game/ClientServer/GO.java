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
    public int boardSize;
    boolean player1Joined = false;
    boolean player2Joined = false;

    public static void main(String[] args) {
        new GO();
    }

    public GO() {
        // starting new server
        try (ServerSocket serverSocket = new ServerSocket(666)){
            System.out.println("Starting server on port 666.");

            while (true) {
                System.out.println("Waiting to join player.");
                // connect first player
                Socket firstPlayer = serverSocket.accept();
                new DataOutputStream(firstPlayer.getOutputStream()).writeInt(1);
                System.out.println("Player one connected.");
                FirstFrame firstFrame = new FirstFrame();

                waitForNewOrLoadGame(firstFrame);

                if (firstFrame.getNewGame()) {
                    SecondFrame secondFrame = new SecondFrame();

                    waitForStartGame();
                    // adding second player or bot

                    gameMode = SecondFrame.getGameMode();
                    boardSize = SecondFrame.getBoardSize();
                    System.out.println(gameMode);
                    System.out.println(boardSize);
                    if (gameMode == 0) {
                        System.out.println("Waiting for second player...");
                        Socket secondPlayer = serverSocket.accept();
                        new DataOutputStream(secondPlayer.getOutputStream()).writeInt(2);
                        player2Joined = true;
                        System.out.println("Player second connected.");

                        GameFrame gameFrame1 = new GameFrame(Color.BLACK);
                        GameFrame gameFrame2 = new GameFrame(Color.WHITE);

                        initializeGame(firstPlayer, secondPlayer, gameFrame1, gameFrame2);
                    } else {
                        System.out.println("Bot connected.");
                        initalizeGameWithBot();
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void waitForStartGame() throws InterruptedException {
        System.out.println("Player one is setting mode of games...");

        while (!(SecondFrame.getStartGame())){
            Thread.sleep(10);
        }
        startGame = true;
    }

    private static boolean getStartGame() {
        return startGame;
    }

    private void waitForNewOrLoadGame(FirstFrame firstFrame) throws InterruptedException {
        while (!(firstFrame.getNewGame() || firstFrame.getLoadGame())){
            Thread.sleep(10);
        }
    }


    private void initializeGame (Socket firstPlayerSocket, Socket secondPlayerSocket, GameFrame gameFrame1, GameFrame gameFrame2) throws InterruptedException {
        System.out.println("Waiting for both players to join...");

        while (!(player2Joined)) {
            Thread.sleep(10);
        }

        System.out.println("Both players joined. Starting game.");
        currentGame = new NewGame(firstPlayerSocket, secondPlayerSocket, gameFrame1, gameFrame2);

        Thread thread = new Thread(currentGame);
        thread.start();
        startGame = false;
    }

    private void initalizeGameWithBot() {
        FirstFrame.startGame = true;
    }
}

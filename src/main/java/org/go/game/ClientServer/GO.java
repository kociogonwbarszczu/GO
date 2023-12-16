package org.go.game.ClientServer;

import org.go.game.FirstFrame;
import org.go.game.SecondFrame;

import java.io.*;
import java.net.*;

public class GO {
    public int gameMode = -1;
    private boolean waiting = true;
    public NewGame currentGame;

    public static void main(String[] args) {
        GO display = new GO();
    }

    public GO() {
        // starting new server
        try (ServerSocket serverSocket = new ServerSocket(666)){
            System.out.println("Starting server on port 666.");

            while (true) {
                // connect first player
                Socket firstPlayer = serverSocket.accept();
                System.out.println("Player one connected.");
                new FirstFrame();
                waitForAmountOfPlayer();
                System.out.println(gameMode);

                // adding second player or bot
                Socket secondPlayer = null;
                if (gameMode == 0) {
                    System.out.println("Waiting for second player...");
                    secondPlayer = serverSocket.accept();
                    System.out.println("Player second connected.");
                } else {
                    System.out.println("Bot connected.");
                }

                initializeGame(firstPlayer, secondPlayer);
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void waitForAmountOfPlayer() throws InterruptedException {
        System.out.println("Player one is setting mode of games...");

        while (waiting) {
            Thread.sleep(10);
            gameMode = SecondFrame.getGameMode();
            if (gameMode > -1) {
                waiting = false;
            }
        }

        System.out.println("Chosen amount of players");
        waiting = true;
    }

    private void initializeGame (Socket firstPlayerSocket, Socket secondePlayerSocket) {
        System.out.println("Starting game.");
        currentGame = new NewGame(firstPlayerSocket, secondePlayerSocket);

        Thread thread = new Thread(currentGame);
        thread.start();
    }
}

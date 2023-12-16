import javax.swing.*;
import java.io.*;
import java.net.*;

public class GO {
    public int numberOfPlayers = 0;
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
                System.out.println(numberOfPlayers);

                // adding second player
                Socket secondPlayer;
                if (numberOfPlayers == 2) {
                    System.out.println("Waiting for second player...");
                    secondPlayer = serverSocket.accept();
                    System.out.println("Player second connected.");
                } else {
                    secondPlayer = serverSocket.accept();
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
            numberOfPlayers = 2;
            if (numberOfPlayers > 0) {
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

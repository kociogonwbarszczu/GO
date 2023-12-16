import java.io.*;
import java.net.*;

public class GO {

    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;

    public static void main(String[] args) {
        new FirstFrame();

        try (ServerSocket serverSocket = new ServerSocket(666)){
            System.out.println("Starting server on port 666");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New player connected");

                new MultiThread(socket).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

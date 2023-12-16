import javafx.scene.control.Cell;

import java.awt.*;
import java.net.*;
import java.io.*;

public class Client {
    private boolean myTurn = false;
    public int sizeOfBoard = 13;
    private Cell [][] cell = new Cell[sizeOfBoard][sizeOfBoard];

    private int rowSelected;
    private int columnSelected;

    private static DataInputStream fromServer;
    private static DataOutputStream toServer;

    private boolean waiting = true;
    private boolean continueToPlay = true;

    public static void main(String[] args) {
        Client display = new Client();
        // WYŚWIETLANIE PLANSZY GRY
        connectToServer();
    }

    private static void connectToServer() {

        try  {
            Socket socket = new Socket("localhost", 666);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }

        Thread thread = new Thread();
        thread.start();
    }

    // showing game frame
    void gameFrame(Frame frame){

    }
}

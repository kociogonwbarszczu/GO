import java.io.*;
import java.net.*;

class MultiThread extends Thread implements Runnable {

    public static int PLAYER1_WON = 1;
    public static int PLAYER2_WON = 2;
    public static int DRAW = 3;
    public static int CONTINUE = 4;

    //sockets
    private Socket socket;
    private Socket secondPlayer;

    public MultiThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));

            //Inicjalizacja  wysylania do socketa
            OutputStream output = socket.getOutputStream();
            PrintWriter out = new PrintWriter(output, true);

            String line;
            do {
                // Odbieranie od socketa
                line = in.readLine();
                // Wypisywanie na serwerze
                System.out.println(line);
                // Wysylanie do socketa
                out.println("-> ("+line+")");

            } while (!line.equals("bye"));

            socket.close();
        } catch (IOException ex) {
            System.err.println("ex");
        }
    }
}

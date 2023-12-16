import java.io.*;
import java.net.*;

class NewGame extends Thread {

    public static int PLAYER1_WON = 1;
    public static int PLAYER2_WON = 2;
    public static int DRAW = 3;
    public static int CONTINUE = 4;

    //sockets
    private Socket firstPlayer;
    private Socket secondPlayer;

    public NewGame(Socket firstPlayer, Socket secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }
    @Override
    public void run() {
        try {
            InputStream input = firstPlayer.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));

            OutputStream output = firstPlayer.getOutputStream();
            PrintWriter out = new PrintWriter(output, true);

            String line;
            do {
                line = in.readLine();
                System.out.println(line);
                out.println("-> ("+line+")");

            } while (!line.equals("bye"));

            firstPlayer.close();
        } catch (IOException ex) {
            System.err.println("ex");
        }
    }
}

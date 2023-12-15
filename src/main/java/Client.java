import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) {

        try  {
            Socket socket = new Socket("localhost", 666);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String text;

            do {
                System.out.println("Enter text: ");
                text = bufferRead.readLine();
                out.println(text);
                System.out.println(in.readLine());

            } while (!text.equals("bye"));
            socket.close();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}

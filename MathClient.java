import java.io.*;
import java.net.*;

public class MathClient {

    public static void main(String[] args) throws IOException {	
        if (args.length != 3) {
            System.out.println("Enter As: java MathClient <hostname> <port> <number>");
            return;
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        String number = args[2];
        Socket socket = new Socket(hostname, port);

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(number);
            String response = in.readLine();
            System.out.println(response);
        } finally {
            socket.close();
        }
    }
}

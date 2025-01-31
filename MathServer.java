/*
 * Group A
 * Members - Raj Bhinde (rmb76@njit.edu)  https://web.njit.edu/~rmb76/
             Ashwini Kanade (ak3374@njit.edu)  https://web.njit.edu/~ak3374/
             Arunav Mishra (am3945@njit.edu)  https://web.njit.edu/~am3945/
             Danielle Scalera (dhs37@njit.edu)  https://web.njit.edu/~dhs37/
             Akash Deore (ad2386@njit.edu)  https://web.njit.edu/~ad2386/
 *                  
 */

import java.io.*;
import java.net.*;

public class MathServer {

    public static void main(String[] args) throws IOException {
        // Validation if user has not entered the port number
        if (args.length != 1) {
            System.out.println("Enter As: java MathServer <port>");  
            return;
        }
        
        int port = Integer.parseInt(args[0]);
    
        // Validation for port number range
        if (port < 2000 || port > 10000) {
            System.out.println("Port number must be between 2000 and 10000.");
            return;
        }

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("MathServer is running on port " + port);
        // Creating a Server socket
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                String received = input.readLine();
                String result = compute(received);
                
                output.println(result);

                clientSocket.close();
            }
        } finally {
            serverSocket.close();
        }
    }

    
    public static String compute(String input) {
        try {
            int n = Integer.parseInt(input);
            // Input validation
            if (n < 0) {
                return "ANS: ERROR (Entered Number is Negative)";
            }
            // Calculating Fibonacci series
            int[] fibSequence = new int[n + 1];
            if (n >= 0) fibSequence[0] = 0;
            if (n >= 1) fibSequence[1] = 1;

            for (int i = 2; i <= n; i++) {
                fibSequence[i] = fibSequence[i - 1] + fibSequence[i - 2];
            }
            // Converting Integer array to String
            StringBuilder result = new StringBuilder("ANS:");
            for (int i = 0; i <= n; i++) {
                result.append(" ").append(fibSequence[i]);
            }

            return result.toString();
        } catch (NumberFormatException e) {
            // Integer input validation
            return "ANS: ERROR (Enter a valid Number)";
        }
    }
}

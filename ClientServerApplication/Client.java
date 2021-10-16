package ClientServerApplication;

import java.io.*;
import java.net.*;
import java.util.*;

// Declare and define the client class
class Client {
    public static void main(String[] args) {
        // Open a socket for connection on localhost:3000
        try (Socket sock = new Socket("localhost", 3000)) {

            // Set autoflush to true to the buffer gets flushed after use
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);

            // Read information from the server into client input
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            // Create an object to get information as user input
            Scanner scanner = new Scanner(System.in);
            String user_input = null;

            // Continue running the client until the user sends exit message
            while (!"exit".equals(user_input)) {

                // Display the Menu for the list of things a client
                System.out.println("-------------------MENU-------------------");
                System.out.println("1. Display TO-DO List");
                System.out.println("2. Display TO-DO List for a certain Client");
                System.out.println("3. Display TO-DO List for a certain Date");
                System.out.println("4. Add a TO-DO to the List");
                System.out.println("5. Remove TO-DOs for a certain Date from the List");
                System.out.println("6. Remove TO-DOs for a certain client from the List");
                System.out.println("7. Request a file containing list from the Server");
                System.out.println("Choose a number between 1-7: ");
                // Get the choice from the user
                int choice = scanner.nextInt();

                // Read user input
                // user_input = scanner.nextLine();

                // Send user input to the server
                out.println(choice);
                out.flush();

                // Display if server has sent any replies

                System.out.println("Server replied: " + in.readLine());

            }

            // Close the scanner object
            scanner.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
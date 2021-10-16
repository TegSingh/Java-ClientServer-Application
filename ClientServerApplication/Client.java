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

                String server_ack = in.readLine();
                System.out.println("Server acknowledged: " + server_ack);
                int server_choice = Integer.parseInt(server_ack);

                switch (server_choice) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;

                    // Add Todo
                    case 4:
                        System.out.println("Enter Action Item [Use Format provided in the README File]:");
                        Scanner sc = new Scanner(System.in);
                        String action_item = sc.nextLine();
                        out.println(action_item);
                        out.flush();

                        System.out.println("Enter Task ID: ");
                        int id = scanner.nextInt();
                        out.println(id);
                        out.flush();

                        System.out.println("Enter year for due date: ");
                        int year = scanner.nextInt();
                        out.println(year);
                        out.flush();

                        System.out.println("Enter month for due date: ");
                        int month = scanner.nextInt();
                        out.println(month);
                        out.flush();

                        System.out.println("Enter day for due date: ");
                        int day = scanner.nextInt();
                        out.println(day);
                        out.flush();

                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    default:
                        break;
                }

            }

            // Close the scanner object
            scanner.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
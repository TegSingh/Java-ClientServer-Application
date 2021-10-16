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
                System.out.println("Enter \"Exit\" to terminate Client process");
                System.out.println("Choose a number between 1-7: ");

                // Get the choice from the user
                int choice = scanner.nextInt();

                // Send user input to the server
                out.println(choice);
                out.flush();

                // Display if server has sent any replies

                String server_ack = in.readLine();
                System.out.println("Server acknowledged: " + server_ack);
                int server_choice = Integer.parseInt(server_ack);

                switch (server_choice) {
                    case 1:
                        String list_items = null;
                        System.out.println("------------------------------");
                        System.out.println("Your current Todo list");

                        list_items = in.readLine();
                        // Read the messages from the server
                        while (!list_items.equals("done")) {
                            System.out.println(list_items);
                            list_items = in.readLine();
                        }

                        System.out.println("------------------------------");
                        break;

                    case 2:
                        String client_list_items = null;
                        System.out.println("------------------------------");
                        System.out.println("Your current Todo list");

                        // Read the messages from the server
                        while ((list_items = in.readLine()) != null) {
                            System.out.println(list_items);
                        }

                        System.out.println("------------------------------");

                        break;

                    case 3:
                        System.out.println("Enter year for due date: ");
                        int year_display = scanner.nextInt();
                        out.println(year_display);
                        out.flush();

                        System.out.println("Enter month for due date: ");
                        int month_display = scanner.nextInt();
                        out.println(month_display);
                        out.flush();

                        System.out.println("Enter day for due date: ");
                        int day_display = scanner.nextInt();
                        out.println(day_display);
                        out.flush();

                        String date_list_items = null;
                        System.out.println("------------------------------");
                        System.out.println("Your current Todo list for date");

                        date_list_items = in.readLine();
                        // Read the messages from the server
                        while (!date_list_items.equals("done")) {
                            System.out.println(date_list_items);
                            date_list_items = in.readLine();
                        }

                        System.out.println("------------------------------");

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

                    // Remove Todos for a date
                    case 5:
                        System.out.println("Enter year for due date: ");
                        int year_delete = scanner.nextInt();
                        out.println(year_delete);
                        out.flush();

                        System.out.println("Enter month for due date: ");
                        int month_delete = scanner.nextInt();
                        out.println(month_delete);
                        out.flush();

                        System.out.println("Enter day for due date: ");
                        int day_delete = scanner.nextInt();
                        out.println(day_delete);
                        out.flush();

                        // Show the list of the removed IDs
                        System.out.println(in.readLine());

                        break;

                    // Remove Todos for a particular client
                    case 6:
                        break;

                    default:
                        System.out.print("Invalid Input try again");
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
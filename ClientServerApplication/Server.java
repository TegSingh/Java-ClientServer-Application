package ClientServerApplication;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.util.*;

import Todo.*;

// Declare and define the Server class
class Server {

    public static void main(String[] args) {

        ServerSocket server = null;
        try {
            server = new ServerSocket(3000);
            server.setReuseAddress(true);

            // Run infinitely until ctrl C interrupt provided
            while (true) {
                Socket client = server.accept();
                System.out.println("New Client IP:" + client.getRemoteSocketAddress());

                // Create a new thread to handle the client
                ClientHandler clientSocket = new ClientHandler(client);
                // Start the Thread for each different client
                new Thread(clientSocket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Each client handler is associated with one thread that handles one client
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        // Make this variable static so all instances share the same instance
        private static Todo_list todo_list = new Todo_list();
        private int id;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            id = Integer.parseInt(clientSocket.getRemoteSocketAddress().toString().split(":")[1]);
        }

        // Method to get client ID
        public int get_client_id() {
            return this.id;
        }

        // Override the run method of the runnable interface
        public void run() {
            PrintWriter out = null;
            BufferedReader in = null;

            try {
                // Get the io streams of the client
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String client_message;

                // While Client messages exist in the buffer
                while ((client_message = in.readLine()) != null) {
                    System.out.println("Client sends: " + client_message);
                    int client_choice = Integer.parseInt(client_message);
                    switch (client_choice) {
                        case 1:
                            System.out.println("Client " + clientSocket.getRemoteSocketAddress()
                                    + " requests Display for To-do List");
                            out.println("1");

                            // Display the To-do list
                            ArrayList<Todo_item> list = todo_list.get_todo_list();
                            for (Todo_item item : list) {
                                out.println(item.toString());
                            }
                            out.println("done");
                            break;

                        case 2:
                            System.out.println("Client " + clientSocket.getRemoteSocketAddress()
                                    + " requests Display for To-do List for itself");
                            out.println("2");

                            // Display the To-do list with the filtered items for a particular client ID
                            ArrayList<Todo_item> list_client = todo_list.get_todo_list();
                            for (Todo_item item : list_client) {
                                if (item.get_id() == this.id)
                                    out.println(item.toString());
                            }
                            out.println("done");

                            break;

                        case 3:
                            System.out.println("Client " + clientSocket.getRemoteSocketAddress()
                                    + " requests Display for To-do List for some date");
                            out.println("3");
                            int year_display = Integer.parseInt(in.readLine());
                            int month_display = Integer.parseInt(in.readLine());
                            int day_display = Integer.parseInt(in.readLine());
                            LocalDate date_display = LocalDate.of(year_display, month_display, day_display);

                            // Display the To-do list with the filtered items
                            ArrayList<Todo_item> list_date = todo_list.get_todo_list();
                            for (Todo_item item : list_date) {
                                if (item.get_dueDate().equals(date_display))
                                    out.println(item.toString());
                            }
                            out.println("done");

                            break;

                        // Adding Todo to the list
                        case 4:
                            System.out.println("Client " + clientSocket.getRemoteSocketAddress()
                                    + " requests Adding a To-do to the list");
                            out.println("4");
                            String action_item = in.readLine();

                            int year = Integer.parseInt(in.readLine());
                            int month = Integer.parseInt(in.readLine());
                            int day = Integer.parseInt(in.readLine());
                            LocalDate dueDate = LocalDate.of(year, month, day);
                            todo_list.add_todo(this.id, action_item, dueDate);
                            break;

                        // Removing item from Todo list given some date
                        case 5:
                            System.out.println("Client " + clientSocket.getRemoteSocketAddress()
                                    + " request Removing an item from To-do List for some date");
                            out.println("5");
                            int year_delete = Integer.parseInt(in.readLine());
                            int month_delete = Integer.parseInt(in.readLine());
                            int day_delete = Integer.parseInt(in.readLine());
                            LocalDate date_delete = LocalDate.of(year_delete, month_delete, day_delete);
                            String removed_list = todo_list.remove_todo(date_delete);
                            out.println(removed_list);
                            break;

                        case 6:
                            System.out.println("Client " + clientSocket.getRemoteSocketAddress()
                                    + " request removing its own To-dos ");
                            String removed_list_client = todo_list.remove_todo_by_id(this.id);
                            out.println("6");
                            out.println("Client ID: " + this.id + " " + removed_list_client);
                            break;

                        default:
                            break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Close io streams for the cliebt
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }

    }

}

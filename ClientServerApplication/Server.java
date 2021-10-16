package ClientServerApplication;

import java.io.*;
import java.net.*;
import java.time.LocalDate;

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

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
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
                            todo_list.display_todo_list();
                            break;

                        case 2:
                            System.out.println("Client " + clientSocket.getRemoteSocketAddress()
                                    + " requests Display for To-do List for itself");
                            out.println("2");
                            break;

                        case 3:
                            System.out.println("Client " + clientSocket.getRemoteSocketAddress()
                                    + " requests Display for To-do List for some date");
                            out.println("3");
                            break;

                        // Adding Todo to the list
                        case 4:
                            System.out.println("Client " + clientSocket.getRemoteSocketAddress()
                                    + " requests Adding a To-do to the list");
                            out.println("4");
                            String action_item = in.readLine();
                            int id = Integer.parseInt(in.readLine());
                            int year = Integer.parseInt(in.readLine());
                            int month = Integer.parseInt(in.readLine());
                            int day = Integer.parseInt(in.readLine());
                            LocalDate dueDate = LocalDate.of(year, month, day);
                            todo_list.add_todo(id, action_item, dueDate);
                            todo_list.display_todo_list();
                            break;

                        case 5:
                            System.out.println("Client " + clientSocket.getRemoteSocketAddress()
                                    + " request Removing an item from To-do List for some date");
                            out.println("5");
                            break;

                        case 6:
                            System.out.println("Client " + clientSocket.getRemoteSocketAddress()
                                    + " request removing its own To-dos ");
                            out.println("6");
                            break;

                        case 7:
                            System.out.println("Client " + clientSocket.getRemoteSocketAddress()
                                    + " Request a file containing the list from the server");
                            out.println("7");
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

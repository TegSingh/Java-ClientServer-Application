import java.io.*;
import java.net.*;

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
                System.out.println("New Client: " + client.getInetAddress().getHostAddress().toString());

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
                    out.println(client_message);
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

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

            // Read information from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            // Create an object to get information as user input
            Scanner scanner = new Scanner(System.in);
            String user_input = null;

            // Continue running the client until the user sends exit message
            while (!"exit".equals(user_input)) {

                // Read user input
                user_input = scanner.nextLine();

                // Send user input to the server
                out.println("User entered: " + user_input);
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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    private static final String SERVER_HOST = "localhost"; // IP Address / Host name
    private static final int SERVER_PORT = 8081;          // Must match the server's port

    public static void main(String[] args) {
        System.out.println("[CLIENT] Attempting to connect to server...");

        // 1. CLIENT SOCKET: Instantiating this automatically attempts a TCP connection handshake
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
            System.out.println("[CLIENT] Successfully connected to server at " + SERVER_HOST + ":" + SERVER_PORT);

            // 2. STREAM I/O SETUP: Network streams and terminal console user input stream
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputToServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("[CLIENT] Type messages below. Type 'QUIT' to exit.");

            String userInput;
            while (true) {
                System.out.print("> ");
                userInput = consoleReader.readLine(); // Wait for user terminal input

                if (userInput == null) break;

                // 3. PROTOCOL TRANSMISSION: Send user text down the socket output stream to the server
                outputToServer.println(userInput);

                // 4. PROTOCOL RECEPTION: Wait for and read the server's incoming echo response string
                String serverResponse = inputFromServer.readLine();
                System.out.println("[CLIENT] Server response -> " + serverResponse);

                if ("QUIT".equalsIgnoreCase(userInput.trim())) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("[CLIENT] Connection error: " + e.getMessage());
        }
        System.out.println("[CLIENT] Disconnected.");
    }
}

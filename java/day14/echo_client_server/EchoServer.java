import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    // PORT BINDING: The server locks down port 8081 to listen for incoming connection traffic
    private static final int PORT = 8081;

    public static void main(String[] args) {
        System.out.println("[SERVER] Starting Echo Server...");

        // 1. SERVERSOCKET: Creates a listening post bound to the specified port
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("[SERVER] Bound to port " + PORT + ". Waiting for a client to connect...");

            // 2. ACCEPTION: This method blocks (freezes) execution until a client connects
            // When a client connects, it returns a standard 'Socket' representing that specific pipeline
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("[SERVER] Client connected from: " + clientSocket.getRemoteSocketAddress());

                // 3. STREAM I/O: Set up input and output streams to talk to the client
                // BufferedReader reads text from the client; PrintWriter writes text back to the client
                BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true); // 'true' enables auto-flush

                String inputLine;
                // 4. PROTOCOL LOOP: Keep reading messages until the client sends 'QUIT' or closes the connection
                while ((inputLine = inputFromClient.readLine()) != null) {
                    System.out.println("[SERVER] Received from client: " + inputLine);
                    
                    if ("QUIT".equalsIgnoreCase(inputLine.trim())) {
                        outputToClient.println("Goodbye!");
                        System.out.println("[SERVER] Client requested disconnect.");
                        break;
                    }

                    // ECHO: Send the exact same message right back down the stream pipeline
                    outputToClient.println("Echo: " + inputLine);
                }
            } // The clientSocket automatically closes here due to try-with-resources
            System.out.println("[SERVER] Client connection closed.");

        } catch (Exception e) {
            System.err.println("[SERVER] Exception encountered: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

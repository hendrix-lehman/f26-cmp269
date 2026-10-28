import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class ChatServer {

  public static final int PORT = 8081;

  public static final String QUIT_COMMAND = "/quit";
  public static final String PM_COMMAND = "/pm";
  public static final String PM_COMMAND_HELP = "/pm <username> <message>";

  private static final Map<String, PrintWriter> activeClients = new ConcurrentHashMap<>();

  // Broadcasting engine
  // Iterates through all active clients and sends the message to each of them
  public static void broadcast(String sender, String message) {
    String formattedMessage = String.format("[%s]: %s", sender, message);
    System.out.println("[BROADCAST]: " + formattedMessage);

    for (PrintWriter writer: activeClients.values()) {
      writer.println(formattedMessage);
    }
  }

  private static class ClientHandler implements Runnable {
    private final Socket socket;
    private String userName;

    public ClientHandler(Socket clientSocket) {
      this.socket = clientSocket;
    }

    @Override
    public void run() {

      try(
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true)
      ) {
        // 1. PROTOCOL: force the client to specify a unique username before joining the chat 
        output.println("ENTER_USERNAME");
        userName = input.readLine();

        while (userName == null || userName.isEmpty() || activeClients.containsKey(userName)) {
          output.println("USERNAME_REJECTED");
          userName = input.readLine();
        }

        // 2. PROTOCOL: username is accepted, add to active clients and notify all other clients
        activeClients.put(userName, output);
        broadcast("[SYSTEM]", userName + " has joined the chat!");

        // 3. EVENT LISTENING LOOP: block and wait for incoming messages from the client, 
        // and broadcast them to all other clients
        String clientMessage;
        while ((clientMessage = input.readLine()) != null) {

          if (clientMessage.equalsIgnoreCase(QUIT_COMMAND)) {
            break;
          }

          if (clientMessage.startsWith(PM_COMMAND)) {
            String[] parts = clientMessage.split(" ", 3);
            if (parts.length < 3) {
              output.println("[SYSTEM] Invalid private message format. Use: " + PM_COMMAND_HELP);
              continue;
            }
            String targetUser = parts[1];
            String privateMessage = parts[2];

            PrintWriter targetWriter = activeClients.get(targetUser);
            if (targetWriter != null) {
              targetWriter.println("[PM from " + userName + "]: " + privateMessage);
              output.println("[PM to " + targetUser + "]: " + privateMessage);
            } else {
              output.println("[SYSTEM] User '" + targetUser + "' not found.");
            }
            continue;
          }

          // pass the string to the broadcast engine to send to all other clients
          broadcast(userName, clientMessage);
        }
      } catch (Exception e) {
        System.out.println("[ChatServer] force disconnecting client " + socket.getRemoteSocketAddress() + " due to error: " + e.getMessage());
      } finally {
        // 4. CLEANUP: remove the client from the active clients list and notify all other clients
        if (userName != null) {
          activeClients.remove(userName);
          broadcast("[SYSTEM]", userName + " has left the chat.");
        }

        try { socket.close(); } catch (IOException ignored) {}
      }
    }
  }

  public static void main(String[] args) {

    System.out.println("[ChatServer] Starting multi-client chat server...");

    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      System.out.println("[ChatServer] Listening on port " + PORT);

      while (true) {
        // Blocks here until any new user clients connects to the server
        Socket clientSocket = serverSocket.accept();
        System.out.println("[ChatServer] New connection handshake from " + clientSocket.getRemoteSocketAddress());

        // Thread-Per-Client pattern: spawn a new thread to handle the new client connection
        ClientHandler handler = new ClientHandler(clientSocket);
        Thread worker = new Thread(handler);
        worker.start();
      }
    } catch (IOException e) {
      System.err.println("[ChatServer] main server loop crashed: " + e.getMessage());
    }
  }

}

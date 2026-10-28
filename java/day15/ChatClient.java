import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ChatClient {

  private static final String HOST = "localhost";

  public static void main(String[] args) {
    
    try (
      Socket socket = new Socket(HOST, ChatServer.PORT);
      BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))
    ) {
      System.out.println("[ChatClient] Handshake successful! Connected to server at " + HOST + ":" + ChatServer.PORT);
      System.out.println("[ChatClient] You can now start sending messages. Type: '" + ChatServer.QUIT_COMMAND + "' to exit.");

      // 1. HANDSHAKE: wait for the server to request a username, then send it
      String serverMessage = input.readLine();
      if ("ENTER_USERNAME".equals(serverMessage)) {
        System.out.print("[ChatClient] Enter your username: ");
        String userName = consoleInput.readLine();
        output.println(userName);
      }
      // handle username rejection and re-entry
      while ((serverMessage = input.readLine()) != null) {
        if ("USERNAME_REJECTED".equals(serverMessage)) {
          System.out.print("[ChatClient] Username rejected. Enter a different username: ");
          String userName = consoleInput.readLine();
          output.println(userName);
        } else {
          break; // username accepted
        }
      }

      // 2. ASYNC LISTENING THREAD: start a thread to listen for incoming messages from the server
      // This prevents our console UI loop from locking while waiting for messages
      Thread listeningThread = new Thread(() -> {
        try {
          String incomingMessage;
          while ((incomingMessage = input.readLine()) != null) {
            System.out.println("\n" + incomingMessage);
            System.out.print("[ChatClient] Enter message: ");
          }
        } catch (Exception e) {
          System.out.println("[ChatClient] Error reading from server: " + e.getMessage());
        }
      });
      listeningThread.start();

      // 3. CONSOLE UI LOOP: block and wait for user input, then send it to the server
      String userInput;
      while (true) {
        System.out.print("[ChatClient] Enter message: ");
        userInput = consoleInput.readLine();

        if (userInput == null || userInput.equalsIgnoreCase(ChatServer.QUIT_COMMAND)) {
          output.println(ChatServer.QUIT_COMMAND);
          break;
        }
        output.println(userInput);
      }


    } catch (Exception e) {
      System.out.println("[ChatClient] Error connecting to server: " + e.getMessage());
    }
  }

}

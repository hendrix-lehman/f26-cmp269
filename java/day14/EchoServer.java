import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;

class EchoServer {

  public static void handleClient(Socket clientSocket) {
    // handle the client connection here
    // add delay to simulate processing time
    try {
      Thread.sleep(5000); // 5 second delay
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    int port = 8081; // default port

    // try-with-resources statement to automatically close the server socket
    try(ServerSocket serverSocket = new ServerSocket(port)) {

      System.out.println("EchoServer listening on port " + port);

      while (true) {
        // block until a client connects
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket.getInetAddress());

        // handle the client connection here
        handleClient(clientSocket);

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
}


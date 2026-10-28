import java.io.IOException;
import java.net.Socket;

public class EchoClient {

  public static final String SERVER_ADDRESS = "localhost";
  public static final int SERVER_PORT = 8081;

  public static void main(String[] args) throws IOException {
    try(Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {

      System.out.println("Connected to server at " + SERVER_ADDRESS + ":" + SERVER_PORT);

    }
  }
}

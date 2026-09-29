import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.nio.charset.StandardCharsets;

class App {

  private static final String BYTE_FILE = "byte_demo.dat";
  private static final String CHAR_FILE = "char_demo.txt";
  private static final String NIO_FILE = "nio_demo.txt";

  private static void byteStreamDemo() {

    byte[] dataToWrite = { 74, 97, 118, 97, 33 }; 

    try (FileOutputStream out = new FileOutputStream(BYTE_FILE);
      FileInputStream in = new FileInputStream(BYTE_FILE)
    ) {
      System.out.println("Writing bytes to file: " + BYTE_FILE);
      out.write(dataToWrite);

      System.out.println("Reading bytes from file: " + BYTE_FILE);
      int byteRead;
      while ((byteRead = in.read()) != -1) {
        System.out.print((char) byteRead);
      }

      System.out.println("\nDone reading bytes from file.");

    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }

  }

  private static void charStreamDemo() {

    String textData = "Java I/O Tutorial 🚀 - Openness & Portability";

    try (FileWriter writer = new FileWriter(CHAR_FILE);
      FileReader reader = new FileReader(CHAR_FILE)
    ) {
      System.out.println("Writing characters to file: " + CHAR_FILE);
      writer.write(textData);

      System.out.println("Reading characters from file: " + CHAR_FILE);
      int charRead;
      while ((charRead = reader.read()) != -1) {
        System.out.print((char) charRead);
      }

      System.out.println("\nDone reading characters from file.");

    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }

  }

  private static void modernNioDemo() {
    Path path = Paths.get(NIO_FILE);

    List<String> lines = List.of(
      "Line 1: NIO makes file handling easier.",
      "Line 2: Built-in support for UTF-8 🚀 encoding.",
      "Line 3: Efficient and modern I/O operations."
    );

    try {
      System.out.println("Writing lines to file: " + NIO_FILE);
      Files.write(path, lines);

      // metadata using NIO
      System.out.println("File exists: " + Files.exists(path));
      System.out.println("File size: " + Files.size(path) + " bytes");
      System.out.println("File last modified: " + Files.getLastModifiedTime(path));

      System.out.println("Reading lines from file: " + NIO_FILE);
      List<String> readLines = Files.readAllLines(path, StandardCharsets.UTF_8);
      // readLines.forEach(System.out::println);
      readLines.forEach(line -> System.out.println("Read line: " + line));

      System.out.println("Done reading lines from file.");

    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }

  }

  public static void main(String[] args) {

    byteStreamDemo();
    charStreamDemo();
    modernNioDemo();
    
  }
}


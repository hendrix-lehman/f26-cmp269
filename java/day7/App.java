import java.util.UnknownFormatConversionException;

class App {

  public static void main(String[] args) {

    int a = 10;
    int b = 1;
    int result = a/b;
    System.out.println("Result: " + result);

    try {
      int c = 10;
      int d = 0;
      int divisionResult = c/d; // This will throw ArithmeticException
      System.out.println("Division Result: " + divisionResult);
    } catch (ArithmeticException e) {
      System.out.println("Error: Division by zero is not allowed.");
    } catch (Exception e) {
      System.out.println("Error: An unexpected error occurred.");
    } finally {
      // this is a good place to release resources or perform cleanup actions
      System.out.println("Finally block executed.");
    }

    try {
      String str = null;
      int length = str.length(); // This will throw NullPointerException
      System.out.println("Length: " + length);
    } catch (NullPointerException e) {
      System.out.println("Error: Attempted to access a method on a null object.");
    } catch (Exception e) {
      System.out.println("Error: An unexpected error occurred. Message: " + e.getMessage());
      e.printStackTrace();
    }

    try {
      String str = "The lucky number is %d";
      int luckyNumber = 7;
      String formattedString = String.format(str, luckyNumber);
      System.out.println("Formatted String: " + formattedString);
    } catch (UnknownFormatConversionException e) {
      System.out.println("Error: Invalid format specifier used in String.format().");
    } catch (Exception e) {
      System.out.println("Error: An unexpected error occurred. Message: " + e.getMessage());
      e.printStackTrace();
    }

    try {
      BankAccount account = new BankAccount();
      account.withdraw(100); // This will throw InsufficientFundsException
    } catch (InsufficientFundsException e) {
      System.out.println("Error: " + e.getMessage() + ". Deficit: " + e.getDeficit());
    } catch (Exception e) {
      System.out.println("Error: An unexpected error occurred. Message: " + e.getMessage());
      e.printStackTrace();
    }

 

    System.out.println("Done.");
  }
}


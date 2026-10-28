abstract class BankAccount {
  protected String accountNumber;
  protected double balance; 

  public BankAccount(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public double getBalance() {
    return balance;
  }

  public void deposit(double amount) {
    if (amount > 0) {
      balance += amount;
    } else {
      System.out.println("Deposit amount must be positive.");
    }
  }

  public abstract void withdraw(double amount);

  @Override
  public String toString() {
    return "BankAccount{accountNumber='" + accountNumber + "', balance=" + balance + "}";
  }

} 

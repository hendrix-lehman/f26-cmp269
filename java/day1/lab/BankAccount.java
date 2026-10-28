public class BankAccount {
    // TODO: Step 1 - Declare private encapsulated fields
  private String accountNumber;
  private String accountHolder;
  private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        // TODO: Step 2 - Implement defensive constructor parameter validation
    if ((accountNumber == null || accountNumber.trim().isEmpty()) || (accountHolder == null || accountHolder.trim().isEmpty())) {
        throw new IllegalArgumentException("Account number and account holder cannot be null or blank.");
    }
    if (initialBalance < 0) {
        throw new IllegalArgumentException("Initial balance cannot be negative.");
    }
    this.accountNumber = accountNumber.trim();
    this.accountHolder = accountHolder.trim();
    this.balance = initialBalance;
    }

    public void deposit(double amount) {
        // TODO: Step 3 - Validate amount > 0, then update balance
    if (amount <= 0) {
        throw new IllegalArgumentException("Deposit amount must be greater than zero.");
    }
    this.balance += amount;
    }

    public boolean withdraw(double amount) {
        // TODO: Step 4 - Validate amount > 0.
        // Check if sufficient funds exist. If yes, deduct and return true; else return false.
    if (amount <= 0) {
        throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
    }
        return this.balance >= amount ? (this.balance -= amount) >= 0 : false;
    }

    public double getBalance() {
        // TODO: Step 5 - Return balance
    return this.balance;
    }

    public String getAccountNumber() {
        // TODO: Step 6 - Return accountNumber
    return this.accountNumber;
    }

    public String getAccountHolder() {
        // TODO: Step 7 - Return accountHolder
    return this.accountHolder;
    }

    public String getSummary() {
        // TODO: Step 8 - Format and return summary string
        // Format: "Account [ACC-101] (Alice Smith): $500.00"
    return String.format("Account [%s] (%s): $%.2f", this.accountNumber, this.accountHolder, this.balance);
    }
}

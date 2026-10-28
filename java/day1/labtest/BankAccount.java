public class BankAccount {
	private final String accountNumber;
	private final String accountHolder;
	private double balance;

	public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.balance = initialBalance;
		// DONE: Step 2 - Implement defensive constructor parameter validation
		if (this.accountNumber == null || this.accountNumber.trim().isBlank()) {
			throw new IllegalArgumentException("NULL OR BLANK accountNumber");
		}
		if (this.accountHolder == null || this.accountHolder.trim().isBlank()) {
			throw new IllegalArgumentException("NULL OR BLANK accountHolder");
		}
		if (initialBalance < 0.0) {
			throw new IllegalArgumentException("initialBalance cannot be negative");
		}
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount MUST BE GREATER THAN 0");
		}
		this.balance += amount;
		// DONE: Step 3 - Validate amount > 0, then update balance
	}

	public boolean withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount MUST BE GREATER THAN 0");
		}
		// DONE: Step 4 - Validate amount > 0.
		if (balance > amount) {
			this.balance -= amount;
			return true;
		}
		
		return false; // returns false
	}

	public double getBalance() {
		// DONE: Step 5 - Return balance
		return balance;
	}

	public String getAccountNumber() {
		// DONE: Step 6 - Return accountNumber
		return accountNumber;
	}

	public String getAccountHolder() {
		// DONE: Step 7 - Return accountHolder
		return accountHolder;
	}

	public String getSummary() {
	    return String.format("Account [%s] (%s): $%.2f", accountNumber, accountHolder, balance);
		// TODO: Step 8 - Format and return summary string
		// Format: "Account [ACC-101] (Alice Smith): $500.00"
	}
}
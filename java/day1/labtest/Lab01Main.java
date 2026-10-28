public class Lab01Main {
    public static void main(String[] args) {
        System.out.println("=== CMP 269 Lab 01: BankAccount Self-Verification ===");

        int passed = 0;
        int failed = 0;

        // Test 1: Account Creation & Summary
        BankAccount acc = new BankAccount("ACC-101", "Alice Smith", 500.0);
        if ("Account [ACC-101] (Alice Smith): $500.00".equals(acc.getSummary())) {
            System.out.println("✔ Test 1 PASSED: Account initialization & getSummary()");
            passed++;
        } else {
            System.err.println("❌ Test 1 FAILED: Expected 'Account [ACC-101] (Alice Smith): $500.00' but got: " + acc.getSummary());
            failed++;
        }

        // Test 2: Valid Deposit
        acc.deposit(250.0);
        if (Math.abs(acc.getBalance() - 750.0) < 0.001) {
            System.out.println("✔ Test 2 PASSED: Deposit operation");
            passed++;
        } else {
            System.err.println("❌ Test 2 FAILED: Expected balance 750.0 but got: " + acc.getBalance());
            failed++;
        }

        // Test 3: Valid Withdrawal
        boolean w1 = acc.withdraw(200.0);
        if (w1 && Math.abs(acc.getBalance() - 550.0) < 0.001) {
            System.out.println("✔ Test 3 PASSED: Valid withdrawal");
            passed++;
        } else {
            System.err.println("❌ Test 3 FAILED: Withdrawal did not succeed as expected.");
            failed++;
        }

        // Test 4: Insufficient Funds Withdrawal
        boolean w2 = acc.withdraw(1000.0);
        if (!w2 && Math.abs(acc.getBalance() - 550.0) < 0.001) {
            System.out.println("✔ Test 4 PASSED: Insufficient funds rejected safely");
            passed++;
        } else {
            System.err.println("❌ Test 4 FAILED: Overdraft was permitted or balance corrupted.");
            failed++;
        }

        // Test 5: Defensive Validation on Null Account Number
        try {
            new BankAccount("", "Bob", 100.0);
            System.err.println("❌ Test 5 FAILED: Blank account number was allowed.");
            failed++;
        } catch (IllegalArgumentException e) {
            System.out.println("✔ Test 5 PASSED: Blank account number rejected with IllegalArgumentException");
            passed++;
        }

        System.out.printf("\nSUMMARY: %d Passed, %d Failed\n", passed, failed);
        if (failed == 0) {
            System.out.println("🎉 ALL LAB 01 TESTS PASSED! Ready for professor checkoff.");
        }
    }
}

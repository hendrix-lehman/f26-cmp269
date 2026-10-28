public class BankAccount {                                                
  private double balance;                                               
                                                                        
  public void withdraw(double amount) throws InsufficientFundsException {                                                                           
    if (amount > balance) {                                           
      throw new InsufficientFundsException("Withdrawal exceeds balance", amount - balance);                                                
    }                                                                 
    this.balance -= amount;                                           
  }                                                                     
}

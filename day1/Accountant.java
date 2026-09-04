class Accountant extends Person {

  private String accountantId;

  public Accountant(String name, int age, String accountantId) {
    super(name, age);
    this.accountantId = accountantId;
  }

  public String getAccountantId() {
    return accountantId;
  }

  public void setAccountantId(String accountantId) {
    this.accountantId = accountantId;
  }
}

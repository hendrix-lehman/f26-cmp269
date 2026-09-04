class App {

  public void printPersonInfo(Person person) {
    System.out.println("Name: " + person.getName());
    System.out.println("Age: " + person.getAge());

    if (person instanceof Accountant) {
      Accountant accountant = (Accountant) person;
      System.out.println("Accountant ID: " + accountant.getAccountantId());
    } else if (person instanceof Student) {
      Student student = (Student) person;
      System.out.println("Student ID: " + student.getStudentId());
      System.out.println("Grade: " + student.getGrade());
    }
  }

  public static void main(String[] args) {

    Accountant accountant = new Accountant("John Doe", 30, "Accountant456");
    Student student = new Student("Jane Smith", 20, "Student123", 90);

    App app = new App();
    app.printPersonInfo(accountant);
    app.printPersonInfo(student);

  }
}

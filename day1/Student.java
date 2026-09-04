class Student extends Person {

  private String studentId;

  private int grade;

  public Student(String name, int age, String studentId, int grade) {
    super(name, age);
    this.studentId = studentId;
    this.grade = grade;
  }

  public String getStudentId() {
    return studentId;
  }

  public int getGrade() {
    return grade;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }
}

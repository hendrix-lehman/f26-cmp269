class LegacyJavaUser {
 
  private final String name;
  private final int age;

  public LegacyJavaUser(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void printJavaGreeting() {
    // String msg = String.format("Hello, %s! You are %d years old.", name, age);
    System.out.println("Hello, " + name + "! You are " + age + " years old.");
  }
}

// class KotlinUser(name: String, age: Int) {
//   fun printKotlinGreeting() {
//       println("Hello, $name! You are $age years old.")
//   }
// }


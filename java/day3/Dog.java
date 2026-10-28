class Dog extends Animal {

  public Dog(String name) {
    super(name);
  }

  @Override
  void sleep() {
    System.out.println(name + " is sleeping.");
  }

  @Override
  void eat() {
    System.out.println(name + " is eating.");
  }

  @Override
  void play() {
    System.out.println(name + " is playing.");
  }

  @Override
  public String toString() {
    return "Dog{name='" + name + "'}";
  }
  
}

abstract class Animal {
  protected String name;

  public Animal(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  abstract void play();

  abstract void eat();

  abstract void sleep();

  @Override
  public String toString() {
    return "Animal{name='" + name + "'}";
  }
}

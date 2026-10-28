// Fish IS-A Animal, CAN swim, and CANNOT play
class Fish extends Animal implements Swimmable {

  // constructor
  public Fish(String name) {
    super(name);
  }

  // implement the abstract method from Animal
  @Override
  public void makeNoise() {
    System.out.println(getName() + " says Blub!");
  }

  // implement the swim method from Swimmable
  @Override
  public void swim() {
    System.out.println(getName() + " is swimming!");
  }
}

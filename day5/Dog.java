// Dog IS-A Animal, CAN swim, and CAN play
class Dog extends Animal implements Swimmable, Playful {

  // constructor
  public Dog(String name) {
    super(name);
  }

  // implement the abstract method from Animal
  @Override
  public void makeNoise() {
    System.out.println(getName() + " says Woof!");
  }

  // implement the swim method from Swimmable
  @Override
  public void swim() {
    System.out.println(getName() + " is swimming!");
  }

  // implement the playWithToy method from Playful
  @Override
  public void playWithToy() {
    System.out.println(getName() + " is playing with a toy!");
  }
}

class Dog extends Animal {
  // Dog class implementation
  public Dog(String name) {
    super(name); // it must the first statement in the constructor, otherwise it will throw a compile-time error
  }

  @Override
  public void eat(String food) {
    System.out.println(getName() + " is eating " + food);
  }

  @Override
  public void makeSound() {
    System.out.println(getName() + " says Woof!");
  }
}

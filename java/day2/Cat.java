final class Cat extends Animal {
  // Cat class implementation
  public Cat(String name) {
    super(name); // it must the first statement in the constructor, otherwise it will throw a compile-time error
  }

  @Override
  public void eat(String food) {
    System.out.println(getName() + " is eating " + food);
  }

  // method signature must match
  // name, return type, parameters, and access modifier
  public void makeSound() {
    System.out.println(getName() + " says Meow!");
  }

  @Override
  public String toString() {
    return "Cat: " + getName();
  }
}

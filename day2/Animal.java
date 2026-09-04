
abstract class Animal /* extends java.lang.Object, if no explicit superclass is specified */ { 
  private String name;

  public Animal(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract void eat(String food);
  // public void eat(String food) {
    // System.out.println(name + " is eating " + food);
  // }

  public abstract void makeSound();
  // public void makeSound() {
    // System.out.println(name + " makes a sound.");
  // }


}


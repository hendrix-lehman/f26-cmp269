// defines WHAT the object is (a type) "Is-A"
// It holds shared state (name) and a constructor
//
// Use an Abstract Class when you want to share closely related code, state, 
// or standard constructors among common subclasses 
// (e.g., all animals have a name and make noise).
abstract class Animal {
  // abstract classes can have instance variables
  private final String name; 

  // abstract classes can have constructors
  public Animal(String name) {
    this.name = name;
  }

  // getter for name
  public String getName() {
    return name;
  }

  // abstract method
  public abstract void makeNoise();

}


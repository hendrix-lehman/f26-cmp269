// 3. Executes the main method of the application
public class App {

  public void letMyAnimalMakeSound(Animal animal) {
    // DO NOT do this!
    //
    // if(animal instanceof Dog) {
      // Dog dog = (Dog) animal;
      // dog.makeSound();
    // } else if(animal instanceof Cat) {
      // Cat cat = (Cat) animal;
      // cat.makeSound();
    // }

    // DO THIS INSTEAD!
    animal.makeSound();
  }

  public static void main(String[] args) {
    // superclass reference points to a Dog object
    Animal myAnimal = new Dog();

    // The JVM resolves this at runtime based on the Dog object. 
    myAnimal.makeSound(); // Output: Dog barks

    // the same superclass reference now points to a Cat object.
    myAnimal = new Cat();

    myAnimal.makeSound(); // Output: Cat meows


  }
}

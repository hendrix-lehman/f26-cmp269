class App {

  public void animalEat(Animal animal, String food) {
    animal.eat(food);
  }

  public void animalSound(Animal animal) {
    animal.makeSound();
  }

  public static void main(String[] args) {
    // Animal myAnimal = new Animal("Generic Animal");
    // System.out.println("Animal class: " + myAnimal.getClass().getName());

    Dog myDog = new Dog("Buddy");
    System.out.println("Dog class: " + myDog.getClass().getName());
    System.out.println("Dog name: " + myDog.getName());

    Cat myCat = new Cat("Whiskers");
    System.out.println("Cat class: " + myCat.getClass().getName());

    App app = new App();
    app.animalEat(myDog, "bone"); // Output: Buddy is eating bone
    app.animalEat(myCat, "fish"); // Output: Whiskers is eating fish
    app.animalSound(myDog); // Output: Buddy says Woof!
    app.animalSound(myCat); // Output: Whiskers says Meow!
  }
}

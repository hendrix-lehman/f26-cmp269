// add package name here if needed
//
class App {



  public static void main(String[] args) {
    
    // create an instance of DogPlayground using the singleton design pattern
    // DogPlayground playground = DogPlayground.getInstance();
    Playground<Dog> playground = PlaygroundFactory.getPlayground();
    Dog buddy = new Dog("Buddy");
    Dog max = new Dog("Max");
    Dog bella = new Dog("Bella");
    playground.addAnimal(buddy);
    playground.addAnimal(max);
    playground.addAnimal(bella);
    playground.playWithAnimals();
    playground.printAnimals();
    playground.removeAnimal(max);
    playground.printAnimals();
  }
}


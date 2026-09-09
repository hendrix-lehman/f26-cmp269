import java.util.ArrayList;
import java.util.List;

// singleton design pattern
public class DogPlayground {

  private static DogPlayground instance;

  private final List<Dog> dogs;

  private DogPlayground() {
    // private constructor to prevent instantiation
    dogs = new ArrayList<>();
  }

  private static class Holder {
    private static final DogPlayground INSTANCE = new DogPlayground();
  } 

  public static DogPlayground getInstance() {
    return Holder.INSTANCE;
    // if (instance == null) {
    //   instance = new DogPlayground();
    // }
    // return instance;
  }

  public synchronized void addDog(Dog dog) {
    dogs.add(dog);
    System.out.println(dog.getName() + " has been added to the playground.");
  }

  public synchronized void removeDog(Dog dog) {
    dogs.remove(dog);
    System.out.println(dog.getName() + " has been removed from the playground.");
  }

  public synchronized void playWithDogs() {
    for (Dog dog : dogs) {
      dog.play();
    }
  }

  public synchronized void printDogs() {
    System.out.println("Dogs in the playground:");
    for (Dog dog : dogs) {
      System.out.println(dog);
    }
  }

}

import java.util.ArrayList;
import java.util.List;

class Playground<T extends Animal> {

  private final List<T> animals;

  public Playground() {
    animals = new ArrayList<>();
  }
  
  // private static class Holder {
  //   private static final Playground<?> INSTANCE = new Playground<>();
  // }

  // public static <T extends Animal> Playground<T> getInstance() {
  //   @SuppressWarnings("unchecked")
  //   Playground<T> instance = (Playground<T>) Holder.INSTANCE;
  //   return instance;
  // }

  public synchronized void addAnimal(T animal) {
    animals.add(animal);
    System.out.println(animal.getName() + " has been added to the playground.");
  }

  public synchronized void removeAnimal(T animal) {
    animals.remove(animal);
    System.out.println(animal.getName() + " has been removed from the playground.");
  }

  public synchronized void playWithAnimals() {
    for (T animal : animals) {
      animal.play();
    }
  }

  public synchronized void printAnimals() {
    System.out.println("Animals in the playground:");
    for (T animal : animals) {
      System.out.println(animal);
    }
  }
}

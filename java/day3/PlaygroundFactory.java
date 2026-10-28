public class PlaygroundFactory {

  public static <T extends Animal> Playground<T> getPlayground() {
    // return Playground.getInstance();
    return (Playground<T>) new Playground<>();
  }
}

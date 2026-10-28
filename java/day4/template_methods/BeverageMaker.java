// 1. the abstract class defining the template method
abstract class BeverageMaker {

  // let's define the beverage making algorithm as a template method
  public final void makeBeverage() {
    boilWater();
    brew();
    pourInCup();
    addCondiments();
  }

  // common steps implemented in the abstract class
  private void boilWater() {
    System.out.println("Boiling water");
  }

  private void pourInCup() {
    System.out.println("Pouring into cup");
  }

  // steps to be implemented by subclasses (Dynamic Dispatch)
  protected abstract void brew();

  protected abstract void addCondiments();

}


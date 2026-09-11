class App {

  public static void main(String[] args) {

    // create a CoffeeMaker and make coffee
    BeverageMaker maker = new CoffeeMaker();
    maker.makeBeverage();

    System.out.println();

    // create a TeaMaker and make tea
    maker = new TeaMaker();
    maker.makeBeverage();
  }
}

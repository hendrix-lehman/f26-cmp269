class InteropRunner {

  public static void main(String[] args) {

    System.out.println("=== 4. JAVA CALLING KOTLIN ===");
    // Kotlin files compile into Java classes automatically.
    // A file named 'KotlinBasicsDemo.kt' will compile into a class named 'KotlinBasicsDemoKt'.
    int result = KotlinBasicsDemoKt.getKotlinSecretFormula(10);

    System.out.println("[Java calling Kotlin] calculated from Kotlin: " + result);

  }

}

fun main() {

  println("=== 1. SYNTAX BASICS & TYPE INFERENCE ===")

  // String languageName = "Kotlin"; // Explicit type declaration
  // val = Immutable (read-only) variable, like 'final' in Java
  val languageName = "Kotlin" // Type inference as String
  // languageName = "Java" // Error: Val cannot be reassigned

  // var = Mutable variable (Read-write)
  var userCount = 10 // Type inference as Int
  userCount = 11 // Perfectly valid, as 'userCount' is mutable

  // Explicit type declaration (only needed if you want to be explicit)
  // totally optional, but can be useful for readability
  val version: Double = 2.1 

  // String interpolation (like f-strings in Python)
  println("Language: $languageName, Version: $version, User Count: $userCount")
  
  println("\n=== 2. EXPRESSIONS (IF & WHEN)  ===")

  val age = 22

  // In Kotlin, 'if' is an EXPRESSION (it returns a value), not just a statement like in Java
  val drivingStatus = if (age >= 16) "Allowed to drive" else "Not allowed to drive"

  println("Driving Status: $drivingStatus")

  // 'when' is a powerful expression in Kotlin, similar to switch-case in other languages
  val lifeStage = when (age) {
    in 0..12 -> "Child"
    in 13..19 -> "Teenager"
    in 20..64 -> "Adult"
    else -> "Senior"
  }
  println("Life stage evaluated to: $lifeStage")

  println("\n=== 3. 2-WAY JVM INTEROPERABILITY ===")

  // Part A: Kotlin calling Java code
  val javaUser = LegacyJavaUser("Alice", 30)
  javaUser.printJavaGreeting() // Calling Java method from Kotlin
  println("[Kotlin calling Java] Fetched data from Java object -> Name: ${javaUser.name}, Age: ${javaUser.age}")

  // Part B: Preparing Kotlin to be called by Java
  println("[Kotlin] preparing internal execution for Java to call...")
  // executeInteropCheck() // This method is designed to be called from Java
}

fun getKotlinSecretFormula(input: Int): Int {
  // A simple Kotlin function that can be called from Java
  return input * 42
}

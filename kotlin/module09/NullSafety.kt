fun main() {
  println("=== 1. COMPILE-TIME NULL SAFETY (T vs T?) ===")

  val nonNullString: String = "Hello"
  // nonNullString = null // This will cause a compile-time error

  var nullableString: String? = "Hello"
  println("nullableString: $nullableString")
  nullableString = null // This is allowed
  println("nullableString: $nullableString")

  // int size = nullableString?.length // This will cause a compile-time error because nullableString can be null
  // to do this correctly, we can use the safe call operator (?.) to safely access the length property of nullableString
  // Safe Call Operator (?.) allows to safely access 'length' property
  // Elvis Operator (?:) allows to provide a default value if the left-hand side is null
  val size = nullableString?.length ?: 0 // This will return 0 if nullableString is null

  println("Size of nullableString: $size")

  println("\n=== 2. SAFE CALL OPERATOR (?.) ===")
  
  val text: String? = null
  
  // Instead of throwing a NullPointerException, this returns 'null' safely if the variable is null
  val length: Int? = text?.length
  println("Safe call length on null: $length") // Prints: null


  println("\n=== 3. ELVIS OPERATOR (?:) ===")
  
  // The Elvis operator provides a fallback default value if the left side evaluates to null
  val absoluteLength: Int = text?.length ?: 0
  println("Fallback length using Elvis: $absoluteLength") // Prints: 0

  // You can also use Elvis to throw exceptions or return early
  // val result = text ?: throw IllegalArgumentException("Text cannot be missing")

  println("\n=== 4. NOT-NULL ASSERTION (!!) ===")
  
  val forcedText: String? = "Kotlin"
  
  // '!!' tells the compiler: "Trust me, I guarantee this is not null. Force-unwrap it."
  val upperCaseText: String = forcedText!!
  println("Forced unwrap output: $upperCaseText")

  // 🛑 WARNING: If the variable actually IS null, '!!' will instantly throw a NullPointerException at runtime!
  // val missingText: String? = null
  // val crashedText = missingText!! // 💥 CRASH!

  println("\n=== 5. SAFE CASTS (as?) ===")
  
  val unknownObj: Any = 123 // This is an Integer

  // A standard cast 'as' will crash the program if the types don't match
  // val badCast = unknownObj as String // 💥 CRASH: ClassCastException

  // 'as?' safely returns null instead of crashing if the cast is invalid
  val safeStringCast: String? = unknownObj as? String
  println("Result of invalid safe cast: $safeStringCast") // Prints: null

  println("\n=== 6. SMART CASTS (is) ===")
  
  val genericData: Any = "Smart casts are amazing!"

  // 'is' checks the data type at runtime
  if (genericData is String) {
      // Inside this block, Kotlin automatically "smart casts" genericData from 'Any' to 'String'.
      // You do NOT need to write: val str = genericData as String
      println("Smart cast success length: ${genericData.length}") // .length is directly available!
  }

  var username: String? = "Hacker123"

  // The Java old way of checking for null before calling a method
  if (username != null) {
    println("username UPPERCASE: ${username.uppercase()}") // Safe to call .uppercase() because we checked for null
  }

  // The Kotlin way using safe call operator
  username?.let {
    println("username UPPERCASE using let: ${it.uppercase()}") // Safe to call .uppercase() because 'it' is guaranteed non-null inside let
  }

  username = null

  // Using safe call operator with let to avoid NullPointerException
  username?.let {
    println("This will NOT print because username is null: ${it.uppercase()}")
  } ?: println("username is null, skipping uppercase operation.")
  

}

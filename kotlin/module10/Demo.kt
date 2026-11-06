
// The primary constructor is defined in the class header
// Default arguments are assigned directly in the signature (e.g., status: String = "Active")
class UserAccount(
  val username: String,
  var email: String,
  var status: String = "Active"
) {

  // Init blocks execute sequentially right after the primary constructor and before any secondary constructors
  init {
    println("[Init Block 1] Primary construtor fields initialized: username=$username, email=$email, status=$status")

    // business logic validation rules:
    // 1. Username must not be empty
    require(username.isNotEmpty()) { "Username cannot be empty" }
    // 2. Email must not be empty
    require(email.isNotEmpty()) { "Email cannot be empty" }
  }

  init {
    // Initialization block to perform additional setup
    println("[Init Block 2] Performing additional setup for UserAccount: username=$username, email=$email, status=$status")

    // business logic to validate email format
    // if (!email.contains("@")) {
      // throw IllegalArgumentException("Invalid email format: $email")
    // }
    require(email.contains("@")) { "Invalid email format: $email" }
  }

  // Secondary Constructor: Must explicitly call the primary constructor using 'this(...)'
  constructor(email: String) : this(
    username = email.substringBefore("@"), // Derive username from email
    email = email,
    status = "Pending Verification" // Default status for this constructor
  ) {
    println("[Secondary Constructor] Created UserAccount with email only: username=$username, email=$email, status=$status")
  }

  // Another secondary constructor that allows for a custom status
  constructor(email: String, status: String) : this(
    username = "NEW USER", 
    email = email,
    status = status
  ) {
    println("[Secondary Constructor] Created UserAccount with custom status: username=$username, email=$email, status=$status")
  }
}

// Data class (compiler-generated magic)
// Declaring a class as 'data' instructs the compiler to automatically generate useful methods 
// like equals(), hashCode(), toString(), and copy() based on the properties defined in the primary constructor.
data class Product(val id: Int, val name: String, val price: Double, val desc: String = "None")

// class Product
// fun copy auto-magically created by the compiler
// fun copy(id: Int = this.id, name: String = this.name, price: Double = this.price): Product {
  // return Product(id, name, price)
// }
// Product prod1 = new Product(1, "Laptop", 999.99);
// Product discountedProduct = new Product(2, prod1.name, 499.99);

fun main() {

  // using Named Arguments to call the primary constructor
  val user1 = UserAccount(email = "hendrix@lehman.edu", username = "Hendrix")
  // without using Named Arguments, the order of parameters must match the constructor signature
  // val user1 = UserAccount("Hendrix", "hendrix@lehman.edu")
  //
  println("User1: username=${user1.username}, email=${user1.email}, status=${user1.status}")

  // trigger the secondary constructor by providing only the email
  val user2 = UserAccount("hendrix@lehman.edu")
  // custom fallback code could be place here to handle any exceptions thrown by the secondary constructor
  println("User2: username=${user2.username}, email=${user2.email}, status=${user2.status}")
  println("$user2")

  // trigger the secondary constructor by providing email and custom status
  val user3 = UserAccount("hendrix@lehman.edu", "Inactive")
  println("User3: username=${user3.username}, email=${user3.email}, status=${user3.status}")


  val prod1 = Product(1, "Laptop", 999.99)
  val prod2 = Product(1, "Laptop", 999.99)

  // Clean toString() representation of the Product object
  println("$prod1")

  //  Structural equality (equals)
  //  Unlike Java's '==', Kotlin's '==' calls the underlying .equals() method automatically
  println("Are prod1 and prod2 structurally equal? ${prod1 == prod2}") // true, because data classes compare property values
  println("Do they share the same hashCode? ${prod1.hashCode() == prod2.hashCode()}") // true, because data classes generate consistent hashCodes based on property values

  val discountedProd = prod1.copy(id = 2, price = 499.99)
  println("Discounted Product: $discountedProd")

  val (id, name, price) = discountedProd // Destructuring declaration
  println("Destructured Discounted Product: id=$id, name=$name, price=$price")

  // java version of destructuring declaration
  // Int id = discountedProd.getId();
  // String name = discountedProd.getName();
  // Double price = discountedProd.getPrice();
  // String desc = discountedProd.getDesc();
}

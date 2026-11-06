// 1. SEALED INTERFACES & CLASSES (Algebraic Data Types)
// A sealed hierarchy strictly limits subclasses to this file only,
// allowing 'when' expressions to be fully exhaustive without an 'else' block
sealed interface PaymentResult

data class PaymentSuccess(val transactionId: String, val amount: Double) : PaymentResult
data class PaymentFailure(val code: Int, val reason: String) : PaymentResult
object PaymentPending : PaymentResult // Using an 'object' singleton for a unique state
object PaymentCancelled : PaymentResult // Another singleton representing a cancelled state

// 2. OBJECT DECLARATION (Singleton Pattern)
// 'object' creates a thread-safe, lazily-initialized singleton instance
object PaymentGatewayConfig {
  val providerName: String = "Stripe-Core"
  var timeoutSeconds: Int = 30

  fun printDebugInfo() {
    println("Payment Gateway: $providerName, Timeout: $timeoutSeconds seconds")
  }
}

// 3. COMPANION OBJECTS
class Order(val orderId: String, val total: Double) {

  // A companion object belongs to the class itself, mimicking static members in other languages
  companion object {
    private var autoIncrementId: Int = 1000

    // Factory method to create an Order with an auto-generated ID
    fun createNewOrder(amount: Double) : Order {
      autoIncrementId++
      return Order("ORD-$autoIncrementId", amount)
    }
  }
}

// 4. EXTENSION FUNCTIONS
// Extends the built-in String class with a brand-new method without inheriting from it
fun String.isValidTransactionId() : Boolean {
  return this.startsWith("TXN-") && this.length > 5
}

// main runner engine 
fun main() {

  println("=== Payment Processing Simulation ===")
  PaymentGatewayConfig.printDebugInfo()

  val newOrder = Order.createNewOrder(150.75)
  println("Created Order: ${newOrder.orderId} with Total: ${newOrder.total}")

  val validTransactionId = "TXN-12345"
  val invalidTransactionId = "12345"

  println("Is '$validTransactionId' a valid transaction ID? ${validTransactionId.isValidTransactionId()}")
  println("Is '$invalidTransactionId' a valid transaction ID? ${invalidTransactionId.isValidTransactionId()}")
  
  // Scope functions (let, apply, run, also)
  // Primarily used to configure properties on an object during initialization
  // Context: 'this', Returns: the object itself
  val localConfig = PaymentGatewayConfig.apply {
    timeoutSeconds = 45 // Modify the singleton's property
  }
  println("Updated Timeout: ${localConfig.timeoutSeconds} seconds")
  PaymentGatewayConfig.printDebugInfo()

  // Used for side-effects like logging or debugging without altering the object
  // Context: 'it', Returns: the object itself
  val finalizedOrder = Order.createNewOrder(89.99).also { // order -> 'it' refers to the newly created Order object
    println("Finalized Order: ${it.orderId} with Total: ${it.total}")
  }

  // Used for transforming values, mapping data types, or handling nullable objects safely
  // Context: 'it', Returns: the object itself
  val receiptMessage: String = finalizedOrder.let { // order -> 'it' refers to the finalized Order object
    "Your total for receipt: ${it.orderId} is ${it.total}"
  }
  println("Let statement output: $receiptMessage")

  // A blend of 'apply' and 'let'. Used to perform configuration AND calculate a final output value
  // Context: 'this', Returns: the last expression in the block
  val stringLength: Int = "Processing Context Entry".run {
    println("Evaluating target string... context variable length is: ${this.length}")
    length // returns the length of the string
  }
  println("Run statement output: $stringLength")

  val outputs: List<PaymentResult> = listOf(
    PaymentSuccess("TXN-001", 100.0),
    PaymentFailure(404, "Transaction Not Found"),
    PaymentSuccess("TXN-002", 250.5),
    PaymentSuccess("TXN-003", 75.25),
    PaymentFailure(500, "Internal Server Error"),
    PaymentCancelled,
    PaymentSuccess("TXN-004", 300.0),
    PaymentPending
  )

  for (result in outputs) {
    when (result) {
      is PaymentSuccess -> println("Payment Successful! Transaction ID: ${result.transactionId}, Amount: ${result.amount}")
      is PaymentFailure -> println("Payment Failed! Code: ${result.code}, Reason: ${result.reason}")
      is PaymentPending -> println("Payment is still pending...")
      PaymentCancelled -> println("Payment has been cancelled.")
    }
  }


}

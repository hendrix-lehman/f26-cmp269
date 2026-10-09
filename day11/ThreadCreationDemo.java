// add package name here if needed
//
class ThreadCreationDemo {

  public static synchronized void printMessage(String message) throws InterruptedException {
    Thread currentThread = Thread.currentThread();
    System.out.println("Thread " + currentThread.getName() + " is executing printMessage.");
    // Thread.sleep(1000); // Simulate some work being done
    Thread.yield(); // Yield control to allow other threads to execute
    System.out.println(message);
  }

  public static synchronized void add(int a, int b) throws InterruptedException {
    int sum = a + b;
    Thread currentThread = Thread.currentThread();
    System.out.println("Thread " + currentThread.getName() + " is executing add.");
    System.out.println("Sum of " + a + " and " + b + " is: " + sum);
  }

  public static void main(String[] args) {
    
    System.out.println("Main thread started.");
    System.out.println("Another message from the main thread.");
    System.out.println("Main thread is about to create a new thread.");

    Runnable task = () -> {
        // System.out.println("New thread started.");
        // System.out.println("Another message from the new thread.");
        // System.out.println("New thread is about to finish.");
      for (int i = 1; i <= 5; i++) {
        System.out.println(Thread.currentThread().getName() + " - Count: " + i);
        try {
          // Thread.sleep(500); // Sleep for 1/2 second
          printMessage("Hello from " + Thread.currentThread().getName() + "!");
          // add(i, i * 2);
        } catch (InterruptedException e) {
          System.out.println(Thread.currentThread().getName() + " was interrupted.");
          break; // Exit the loop if interrupted
        }
      }
    };

    Thread worker1 = new Thread(task, "Worker-1");
    Thread worker2 = new Thread(task, "Worker-2");

    // spawns new OS-level threads and executes the run() method of the Runnable object
    worker1.start(); 
    worker2.start();

    // the main thread continues executing concurrently with the new threads
    System.out.println("Done");
  }
}


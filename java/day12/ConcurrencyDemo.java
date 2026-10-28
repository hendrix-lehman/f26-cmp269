public class ConcurrencyDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread started.");

        // === THREAD CREATION APPROACHES ===
        
        // Approach 2: Subclassing Thread
        MyThread thread1 = new MyThread(); 
        thread1.setName("Thread-A");

        // Approach 1: Implementing Runnable (Preferred for flexibility and object reuse)
        MyRunnable sharedResource = new MyRunnable();
        Thread thread2 = new Thread(sharedResource, "Thread-B");
        Thread thread3 = new Thread(sharedResource, "Thread-C");

        // === THREAD LIFECYCLE DEMONSTRATION ===
        
        // State: NEW (Created but not started)
        System.out.println(thread1.getName() + " state after creation: " + thread1.getState());

        // State: RUNNABLE (Ready to run or running)
        thread1.start();
        System.out.println(thread1.getName() + " state after start(): " + thread1.getState());

        // Start the Runnable threads sharing the same heap resource
        thread2.start();
        thread3.start();

        // Let them run briefly, then inspect thread1 while it sleeps
        Thread.sleep(200);
        // State: TIMED_WAITING (Sleeping inside its run method)
        System.out.println(thread1.getName() + " state while sleeping: " + thread1.getState());

        // Wait for threads to complete
        thread1.join();
        thread2.join();
        thread3.join();

        // State: TERMINATED (Finished execution)
        System.out.println(thread1.getName() + " state after completion: " + thread1.getState());
        System.out.println("Main thread finished.");
    }
}

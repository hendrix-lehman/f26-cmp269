// 2. Runnable Interface Approach
class MyRunnable implements Runnable {
    // Shared Heap Data: All threads sharing this Runnable instance share this variable
    private int sharedCounter = 0; 

  // Decouples task from thread management, allowing for more flexible thread handling and reusability

    @Override
    public void run() {
        // Private Stack Data: Every thread gets its own isolated copy of this variable
        int localCounter = 0; 

        for (int i = 0; i < 3; i++) {
            sharedCounter++; // Unsafe shared access (race condition potential without synchronization)
            localCounter++;
            
            System.out.println(Thread.currentThread().getName() + 
                " | Shared Heap Counter: " + sharedCounter + 
                " | Private Stack Counter: " + localCounter);
            
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
        }
    }
}

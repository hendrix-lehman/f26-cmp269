// 1. Thread Class Approach
class MyThread extends Thread {

  // Tightly couples task to the Thread class, limiting flexibility and reusability
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " (Thread Class) is running.");
        try {
            // Moving to TIMED_WAITING state
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
        System.out.println(Thread.currentThread().getName() + " is finished.");
    }
}

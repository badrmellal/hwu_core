public class Concurrency {

    // Simple test using extend Thread
    static class Test extends Thread {
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }

    // now using Runnable
    static class Task implements Runnable {
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " Count: " + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        System.out.println("Everything is running");
//        Test t1 = new Test();
//        t1.start();
//        Test t2 = new Test();
//        t2.start();
//        t1.join();

        Task myTask = new Task();
        Thread t1 = new Thread(myTask, "Thread1");
        t1.setPriority(Thread.MAX_PRIORITY);
        Thread t2 = new Thread(myTask, "Thread2");
        t1.start();
        t2.start();
        t1.join();

        System.out.println("Main thread");
    }
}

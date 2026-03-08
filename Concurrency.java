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
        Test t1 = new Test();
        t1.start();
        Test t2 = new Test();
        t2.start();
        t2.join();
//        Task myTask = new Task();
//        Thread t1 = new Thread(myTask, "Thread1");
//        Thread t2 = new Thread(myTask, "Thread2");
//        t1.start();
//        t2.start();
    }
}

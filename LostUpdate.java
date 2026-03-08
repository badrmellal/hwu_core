public class LostUpdate implements Runnable {
    private int compte = 0;

    @Override
    public void run() {
        System.out.println("Start Compte: " + compte);
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 10000; i++) {
            synchronized(Empty.class){
                compte++;
            }
        }
    }

    static class Empty{}

    static class SalleCinema {
        private int placesDisponibles = 10;

        // The synchronized keyword ensures only one client books at a time
        public synchronized void reserver(int nb, String client) {
            if (placesDisponibles >= nb) {
                placesDisponibles -= nb;
                System.out.println("Places restantes : " + placesDisponibles);
            } else {
                System.out.println("Plus assez de places pour " + client);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LostUpdate lost = new LostUpdate();
        Thread lostThread1 = new Thread(lost);
        Thread lostThread2 = new Thread(lost);
        lostThread1.start();
        lostThread2.start();

        lostThread1.join();
        lostThread2.join();
        System.out.println("Final compte: " + lost.compte);
        System.out.println(System.currentTimeMillis());

        SalleCinema obj = new SalleCinema();
        obj.reserver(5, " ");
        System.out.println(System.currentTimeMillis());

    }
}

public class Additionneur extends Thread {

    // Shared total sum between all threads
    private static int sommeTotale = 0;

    // Explicit monitor lock
    private static final Object cle = new Object();

    // Variables for each thread
    private int debut;
    private int fin;
    private int[] tableau;

    // Constructor
    public Additionneur(int[] tableau, int debut, int fin) {
        this.tableau = tableau;
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public void run() {

        int sommePartielle = 0;

        // 1. Calculate locally WITHOUT locking (fast)
        for (int i = debut; i < fin; i++) {
            sommePartielle += tableau[i];
        }

        // 2. Lock only when updating shared variable
        synchronized (cle) {
            sommeTotale += sommePartielle;
        }
    }

    // Method to get the final sum
    public static int getSommeTotale() {
        return sommeTotale;
    }

    public static void main(String[] args) throws InterruptedException {

        int[] tableau = new int[1000];

        // Fill array
        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = 1;
        }

        int nbThreads = 4;
        Additionneur[] threads = new Additionneur[nbThreads];

        int taille = tableau.length / nbThreads;

        // Create threads
        for (int i = 0; i < nbThreads; i++) {
            int debut = i * taille;
            int fin = (i == nbThreads - 1) ? tableau.length : debut + taille;

            threads[i] = new Additionneur(tableau, debut, fin);
            threads[i].start();
        }

        // Wait for all threads
        for (Additionneur t : threads) {
            t.join();
        }

        System.out.println("Somme totale = " + Additionneur.getSommeTotale());
    }
}

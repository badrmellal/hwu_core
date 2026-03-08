public class GestionCompte implements Runnable {
    private int solde = 0; // shared resource
    private String action;
    private int montant;

    public GestionCompte(String ajouter, int i) {
    }

    public synchronized void retirer(int montant) throws InterruptedException {
        while (solde < montant){
            System.out.println(Thread.currentThread().getName() + ": solde = " + solde + " waiting...");
            wait();
        }
        solde -= montant;
        System.out.println(Thread.currentThread().getName() + ": solde = " + solde + " done.");
    }

    public synchronized void ajouter(int montant){
        System.out.println(this.getClass().getName() + " Nouveau solde : " + solde);
        solde += montant;
        notify();
    }

    public void run(){
        try {
            if (action.equals("retirer")) {
                retirer(montant);
            } else if (action.equals("ajouter")) {
                ajouter(montant);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        GestionCompte compte = new GestionCompte("ajouter", 0);

        Thread t1 = new Thread(() -> {
            try {
                compte.retirer(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Client-1");

        Thread t2 = new Thread(() -> {
            compte.ajouter(200);
        }, "Client-2");

        t1.start();
        t2.start();
    }
}

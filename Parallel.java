public class Parallel {

    // Shared bank account
    public static class Compte {
        private int balance = 0;

        public synchronized void deposit(int amount){
            balance = balance + amount;
        }

        public synchronized void withdraw(int amount){
            balance = balance - amount;
        }

        public int getBalance(){
            return balance;
        }
    }

    // Client = Thread
    public static class Client extends Thread {
        private Compte compte;
        private String name;

        public Client(String name, Compte compte){
            this.name = name;
            this.compte = compte;
        }

        public void run(){
            for(int i = 0; i < 1000; i++){
                compte.deposit(1);
                compte.withdraw(1);
            }
            System.out.println(name + " finished.");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Compte compte = new Compte();

        Client c1 = new Client("Client 1", compte);
        Client c2 = new Client("Client 2", compte);
        Client c3 = new Client("Client 3", compte);

        c1.start();
        c2.start();
        c3.start();

        c1.join();
        c2.join();
        c3.join();

        System.out.println("Final balance: " + compte.getBalance());
    }
}

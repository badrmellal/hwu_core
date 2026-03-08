import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Mandelbrot {

    // Dimensions de l'image
    static final int WIDTH = 3000;
    static final int HEIGHT = 2000;
    static final int ITERATION_MAX = 1000;

    public static void main(String[] args) {
        // Création des images pour stocker les pixels calculés
        BufferedImage imageSequentielle = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage imageParallele = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        // 1. CALCUL SÉQUENTIEL
        long debutSeq = System.currentTimeMillis();
        calculerSequentiel(imageSequentielle);
        long finSeq = System.currentTimeMillis();
        long tempsSeq = finSeq - debutSeq;

        // 2. CALCUL PARALLÈLE

        // Le nombre de threads est exactement égal au nombre de coeurs de la machine
        int nbCoeurs = Runtime.getRuntime().availableProcessors();

        long debutPar = System.currentTimeMillis();
        calculerParallele(imageParallele, nbCoeurs);
        long finPar = System.currentTimeMillis();
        long tempsPar = finPar - debutPar;

        // 3. AFFICHAGE DES RÉSULTATS DANS LA CONSOLE
        System.out.println("Nombre de coeurs détectés : " + nbCoeurs);
        System.out.println("Temps d'exécution Séquentiel : " + tempsSeq + " ms");
        System.out.println("Temps d'exécution Parallèle  : " + tempsPar + " ms");

        // Affichage graphique du résultat final
        afficherFenetre(imageParallele);
    }


    //  Algorithme pour le calcul d'un pixel

    public static void dessinerPixel(BufferedImage image, int x, int y) {
        // Mapping des coordonnées de l'image vers le plan complexe
        double c_r = -2.5 + (4.0 * x / WIDTH);
        double c_i = -1.5 + (3.0 * y / HEIGHT);

        double z_r = 0;
        double z_i = 0;
        int i = 0;

        // Boucle avec les conditions d'arrêt
        while (z_r * z_r + z_i * z_i < 4 && i < ITERATION_MAX) {
            double tmp = z_r; // On sauvegarde l'ancien z_r
            z_r = z_r * z_r - z_i * z_i + c_r;
            z_i = 2 * tmp * z_i + c_i; // On utilise tmp ici
            i++;
        }

        // Coloration noir/blanc
        if (i == ITERATION_MAX) {
            image.setRGB(x, y, Color.BLACK.getRGB());
        } else {
            image.setRGB(x, y, Color.WHITE.getRGB());
        }
    }


     // Approche classique avec un seul processus

    public static void calculerSequentiel(BufferedImage image) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                dessinerPixel(image, x, y);
            }
        }
    }


    // Définition du Thread

    static class MandelbrotThread extends Thread {
        private int debutY, finY;
        private BufferedImage image;

        public MandelbrotThread(BufferedImage image, int debutY, int finY) {
            this.image = image;
            this.debutY = debutY;
            this.finY = finY;
        }

        @Override
        public void run() {
            // Le thread ne travaille que sur son bloc de lignes (debutY à finY)
            for (int y = debutY; y < finY; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    dessinerPixel(image, x, y);
                }
            }
        }
    }


     // Création, lancement et synchronisation des threads

    public static void calculerParallele(BufferedImage image, int nbThreads) {
        MandelbrotThread[] threads = new MandelbrotThread[nbThreads];
        int blocLignes = HEIGHT / nbThreads;

        // 1. Instanciation et appel de start()
        for (int i = 0; i < nbThreads; i++) {
            int debutY = i * blocLignes;
            int finY = (i == nbThreads - 1) ? HEIGHT : (i + 1) * blocLignes;

            threads[i] = new MandelbrotThread(image, debutY, finY);
            threads[i].start();
        }

        // 2. Synchronisation sur terminaison
        for (int i = 0; i < nbThreads; i++) {
            try {
                threads[i].join(); // Le main attend que le thread i termine
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static void afficherFenetre(BufferedImage image) {
        JFrame frame = new JFrame("Fractale de Mandelbrot ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JLabel(new ImageIcon(image)));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

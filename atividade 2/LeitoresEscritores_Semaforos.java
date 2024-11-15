import java.util.concurrent.Semaphore;

public class LeitoresEscritores_Semaforos {

    private static final Semaphore mutexLeitores = new Semaphore(1);
    private static final Semaphore mutexEscritores = new Semaphore(1);
    private static final Semaphore leitores = new Semaphore(1);
    private static int numLeitores = 0;

    public static void main(String[] args) {
        Thread leitor1 = new Thread(new Leitor(), "Leitor 1");
        Thread leitor2 = new Thread(new Leitor(), "Leitor 2");
        Thread escritor = new Thread(new Escritor(), "Escritor");

        leitor1.start();
        leitor2.start();
        escritor.start();
    }

    static class Leitor implements Runnable {
        public void run() {
            try {
                mutexLeitores.acquire();
                numLeitores++;
                if (numLeitores == 1) {
                    leitores.acquire(); // Impede escritores
                }
                mutexLeitores.release();

                System.out.println(Thread.currentThread().getName() + " está lendo.");
                Thread.sleep(1000);

                mutexLeitores.acquire();
                numLeitores--;
                if (numLeitores == 0) {
                    leitores.release(); // Libera escritores
                }
                mutexLeitores.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Escritor implements Runnable {
        public void run() {
            try {
                leitores.acquire(); // Impede leitores e escritores
                mutexEscritores.acquire();

                System.out.println(Thread.currentThread().getName() + " está escrevendo.");
                Thread.sleep(1000);

                mutexEscritores.release();
                leitores.release(); // Libera leitores e escritores
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class LeitoresEscritores_Barreiras {

    private static final CyclicBarrier barreira = new CyclicBarrier(2);

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
                System.out.println(Thread.currentThread().getName() + " está lendo.");
                Thread.sleep(1000);
                barreira.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Escritor implements Runnable {
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " está escrevendo.");
                Thread.sleep(1000);
                barreira.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

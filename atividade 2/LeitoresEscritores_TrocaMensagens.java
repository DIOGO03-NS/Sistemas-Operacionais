import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LeitoresEscritores_TrocaMensagens {

    private static final BlockingQueue<String> fila = new ArrayBlockingQueue<>(10);

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
                fila.put("Leitura");
                System.out.println(Thread.currentThread().getName() + " está lendo.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Escritor implements Runnable {
        public void run() {
            try {
                fila.put("Escrita");
                System.out.println(Thread.currentThread().getName() + " está escrevendo.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class LeitoresEscritores_Mutex {

    private static final Object mutexLeitores = new Object();
    private static final Object mutexEscritores = new Object();
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
            synchronized (mutexLeitores) {
                numLeitores++;
                if (numLeitores == 1) {
                    synchronized (mutexEscritores) {
                        // Bloqueia escritores
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + " está lendo.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            synchronized (mutexLeitores) {
                numLeitores--;
                if (numLeitores == 0) {
                    synchronized (mutexEscritores) {
                        // Libera escritores
                    }
                }
            }
        }
    }

    static class Escritor implements Runnable {
        public void run() {
            synchronized (mutexEscritores) {
                System.out.println(Thread.currentThread().getName() + " está escrevendo.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

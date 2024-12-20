public class Main {
    public static void main(String[] args) {
        System.out.println("Semáforos");
        LeitoresEscritores_Semaforos.main(args);

        System.out.println("\nMutex");
        LeitoresEscritores_Mutex.main(args);

        System.out.println("\nMonitores");
        LeitoresEscritores_Monitores.main(args);

        System.out.println("\nTroca de Mensagens");
        LeitoresEscritores_TrocaMensagens.main(args);

        System.out.println("\nBarreiras");
        LeitoresEscritores_Barreiras.main(args);
    }
}

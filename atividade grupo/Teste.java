import java.util.ArrayList;
import java.util.List;

public class Teste {
    public static void main(String[] args) {
        // Criar lista de processos
        List<Processo> processos = new ArrayList<>();
        processos.add(new Processo("P1", 0, 5, 2));
        processos.add(new Processo("P2", 2, 3, 1));
        processos.add(new Processo("P3", 4, 8, 3));
        processos.add(new Processo("P4", 5, 6, 2));
        processos.add(new Processo("P5", 11, 8, 1));

        System.out.println("------------------------------------------------");

        // Instanciar o escalonador e executar o FCFS
        FirstComeFirstServed escalonador = new FirstComeFirstServed();
        escalonador.executarFCFS(processos);

        System.out.println("------------------------------------------------");

        // Instanciar o escalonador e executar o SJF
        ShortestJobFirst escalonador2 = new ShortestJobFirst();
        escalonador2.executarSJF(processos);

        System.out.println("------------------------------------------------");

        // Instanciar o escalonador e executar o Priority Scheduling
        PriorityScheduling escalonador3 = new PriorityScheduling();
        escalonador3.executarPriorityScheduling(processos);

        System.out.println("------------------------------------------------");

        // Instanciar o escalonador e executar o Round Robin
        RoundRobin escalonador4 = new RoundRobin(2);
        escalonador4.executarRoundRobin(processos);

        System.out.println("------------------------------------------------");

    }
}

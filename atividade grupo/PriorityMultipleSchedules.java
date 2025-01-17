import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriorityMultipleSchedules {

    public void executarPriorityMultipleQueues(List<Processo> processos) {
        // Mapear filas de prioridade (prioridade como chave)
        Map<Integer, List<Processo>> filas = new HashMap<>();
        for (Processo processo : processos) {
            filas.computeIfAbsent(processo.getPrioridade(), k -> new ArrayList<>()).add(processo);
        }

        // Ordenar as prioridades (menor número = maior prioridade)
        List<Integer> prioridadesOrdenadas = new ArrayList<>(filas.keySet());
        prioridadesOrdenadas.sort(Comparator.naturalOrder());

        int tempoAtual = 0;
        double somaEspera = 0;
        double somaRetorno = 0;

        System.out.println("Priority Scheduling - Multiple Queues");

        System.out.print("Ordem de Execução: ");

        for (int prioridade : prioridadesOrdenadas) {
            List<Processo> fila = filas.get(prioridade);

            // Ordenar os processos na fila por tempo de chegada (FCFS dentro da fila)
            fila.sort(Comparator.comparingInt(Processo::getTempoChegada));

            for (int i = 0; i < fila.size(); i++) {
                Processo processo = fila.get(i);

                // Exibir ordem de execução
                System.out.print(processo.getId());
                if (i < fila.size() - 1 || prioridade != prioridadesOrdenadas.get(prioridadesOrdenadas.size() - 1)) {
                    System.out.print(" -> ");
                }

                // Atualizar tempos do processo
                if (tempoAtual < processo.getTempoChegada()) {
                    tempoAtual = processo.getTempoChegada();
                }
                processo.setTempoEspera(tempoAtual - processo.getTempoChegada());
                tempoAtual += processo.getTempoExecucao();
                processo.setTempoRetorno(tempoAtual - processo.getTempoChegada());

                // Somar tempos para cálculo médio
                somaEspera += processo.getTempoEspera();
                somaRetorno += processo.getTempoRetorno();
            }
        }

        System.out.println("\n");

        // Exibir tempos de espera e retorno
        System.out.println("Processo | Prioridade | Tempo de Espera | Tempo de Retorno");
        for (Processo processo : processos) {
            System.out.printf("%s        | %d          | %d              | %d\n",
                    processo.getId(), processo.getPrioridade(), processo.getTempoEspera(), processo.getTempoRetorno());
        }

        // Exibir tempos médios
        double tempoMedioEspera = somaEspera / processos.size();
        double tempoMedioRetorno = somaRetorno / processos.size();

        System.out.println();
        System.out.printf("Tempo Médio de Espera: %.2f\n", tempoMedioEspera);
        System.out.printf("Tempo Médio de Retorno: %.2f\n", tempoMedioRetorno);
    }
}

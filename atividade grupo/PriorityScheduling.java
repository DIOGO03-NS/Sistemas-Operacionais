import java.util.Comparator;
import java.util.List;

public class PriorityScheduling {

    public void executarPriorityScheduling(List<Processo> processos) {
        // Ordenar processos por prioridade (menor valor = maior prioridade)
        processos.sort(Comparator.comparingInt(Processo::getPrioridade));

        int tempoAtual = 0;
        double somaEspera = 0;
        double somaRetorno = 0;

        System.out.println("Priority Scheduling (Não Preemptivo)");
        System.out.print("Ordem de Execução: ");

        for (int i = 0; i < processos.size(); i++) {
            Processo processo = processos.get(i);

            // Adicionar ID na ordem de execução
            System.out.print(processo.getId());
            if (i < processos.size() - 1) {
                System.out.print(" -> ");
            }

            // Atualizar tempos
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

        System.out.println("\n");
        System.out.println("Processo | Tempo de Espera | Tempo de Retorno");

        for (Processo processo : processos) {
            System.out.printf("%s        | %d              | %d\n",
                    processo.getId(), processo.getTempoEspera(), processo.getTempoRetorno());
        }

        // Cálculo de médias
        double tempoMedioEspera = somaEspera / processos.size();
        double tempoMedioRetorno = somaRetorno / processos.size();

        System.out.println();
        System.out.printf("Tempo Médio de Espera: %.2f\n", tempoMedioEspera);
        System.out.printf("Tempo Médio de Retorno: %.2f\n", tempoMedioRetorno);
    }
}
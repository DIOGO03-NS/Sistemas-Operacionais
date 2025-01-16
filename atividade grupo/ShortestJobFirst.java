import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShortestJobFirst {

    public void executarSJF(List<Processo> processos) {
        List<Processo> filaProntos = new ArrayList<>(processos); // Copiar a lista original
        List<Processo> ordemExecucao = new ArrayList<>(); // Ordem final de execução
        int tempoAtual = 0;
        double somaEspera = 0;
        double somaRetorno = 0;

        System.out.println("SJF - Shortest Job First");

        while (!filaProntos.isEmpty()) {
            // Filtrar processos que chegaram até o tempo atual
            List<Processo> candidatos = new ArrayList<>();
            for (Processo processo : filaProntos) {
                if (processo.getTempoChegada() <= tempoAtual) {
                    candidatos.add(processo);
                }
            }

            // Escolher o processo com menor tempo de execução
            Processo proximo;
            if (!candidatos.isEmpty()) {
                proximo = candidatos.stream()
                        .min(Comparator.comparingInt(Processo::getTempoExecucao))
                        .orElseThrow();
            } else {
                // Se não houver candidatos, avançar o tempo até o próximo processo
                proximo = filaProntos.stream()
                        .min(Comparator.comparingInt(Processo::getTempoChegada))
                        .orElseThrow();
                tempoAtual = proximo.getTempoChegada();
            }

            // Atualizar tempos do processo escolhido
            ordemExecucao.add(proximo);
            filaProntos.remove(proximo);

            proximo.setTempoEspera(tempoAtual - proximo.getTempoChegada());
            tempoAtual += proximo.getTempoExecucao();
            proximo.setTempoRetorno(tempoAtual - proximo.getTempoChegada());

            // Somar tempos para cálculo médio
            somaEspera += proximo.getTempoEspera();
            somaRetorno += proximo.getTempoRetorno();
        }

        // Exibir a ordem de execução
        System.out.print("Ordem de Execução: ");
        for (int i = 0; i < ordemExecucao.size(); i++) {
            System.out.print(ordemExecucao.get(i).getId());
            if (i < ordemExecucao.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("\n");

        // Exibir tempos de espera e retorno
        System.out.println("Processo | Tempo de Espera | Tempo de Retorno");
        for (Processo processo : ordemExecucao) {
            System.out.printf("%s        | %d              | %d\n",
                    processo.getId(), processo.getTempoEspera(), processo.getTempoRetorno());
        }

        // Exibir tempos médios
        double tempoMedioEspera = somaEspera / processos.size();
        double tempoMedioRetorno = somaRetorno / processos.size();

        System.out.println();
        System.out.printf("Tempo Médio de Espera: %.2f\n", tempoMedioEspera);
        System.out.printf("Tempo Médio de Retorno: %.2f\n", tempoMedioRetorno);
    }
}

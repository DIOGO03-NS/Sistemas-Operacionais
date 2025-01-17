import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoteryScheduling {

    public void executarLottery(List<Processo> processos) {
        Random random = new Random();
        int tempoAtual = 0;
        double somaEspera = 0;
        double somaRetorno = 0;

        // Criar lista de bilhetes
        List<Processo> bilhetes = new ArrayList<>();
        for (Processo processo : processos) {
            // Cada processo recebe bilhetes com base na prioridade (mais bilhetes para
            // prioridades menores)
            int bilhetesProcesso = 10 - processo.getPrioridade(); // Exemplo: prioridade alta (1) dá mais bilhetes
            for (int i = 0; i < bilhetesProcesso; i++) {
                bilhetes.add(processo);
            }
        }

        System.out.println("Lottery Scheduling");

        System.out.print("Ordem de Execução: ");

        while (!processos.isEmpty()) {
            // Sortear um bilhete
            Processo processoSorteado = bilhetes.get(random.nextInt(bilhetes.size()));

            // Se o processo sorteado já chegou no tempo atual, executa
            if (processoSorteado.getTempoChegada() <= tempoAtual) {
                System.out.print(processoSorteado.getId());
                if (processos.size() > 1) {
                    System.out.print(" -> ");
                }

                // Atualizar tempos
                processoSorteado.setTempoEspera(tempoAtual - processoSorteado.getTempoChegada());
                tempoAtual += processoSorteado.getTempoExecucao();
                processoSorteado.setTempoRetorno(tempoAtual - processoSorteado.getTempoChegada());

                // Somar tempos para cálculo médio
                somaEspera += processoSorteado.getTempoEspera();
                somaRetorno += processoSorteado.getTempoRetorno();

                // Remover processo da lista e seus bilhetes
                processos.remove(processoSorteado);
                bilhetes.removeIf(p -> p == processoSorteado);
            } else {
                // Avançar o tempo se nenhum processo sorteado puder ser executado
                tempoAtual++;
            }
        }

        System.out.println("\n");
        System.out.println("Processo | Tempo de Espera | Tempo de Retorno");
        for (Processo processo : processos) {
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

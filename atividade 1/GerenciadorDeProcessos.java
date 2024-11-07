import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeProcessos {
    private List<Processo> processos; 
    private int quantum = 1000;       

    public GerenciadorDeProcessos() {
        processos = new ArrayList<>();
    }

    public void adicionarProcesso(Processo processo) {
        processos.add(processo);
    }

    public void iniciarExecucao() {
        boolean processosAtivos = true;

        while (processosAtivos) {
            processosAtivos = false;
            
            for (Processo processo : processos) {
                if (!processo.getEstado().equals("Finalizado")) {

                    if (processo.getEstado().equals("Pronto")) {
                        processo.trocaDeContexto("Executando");
                        processo.executarQuantum();
                    }
                    
                    if (processo.getEstado().equals("Bloqueado")) {
                        if (processo.podeDesbloquear()) {
                            processo.trocaDeContexto("Pronto");
                        }
                    }
                }
            }
        }
        System.out.println("Todos os processos foram finalizados.");
    }
}

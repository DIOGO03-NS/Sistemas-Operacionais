import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Processo {
    private int pid;        
    private int tp;     
    private int cp;          
    private String ep;       
    private int nes;         
    private int nCpu;         
    private int tempoExecucao; 

    private static final int QUANTUM = 1000;
    private Random random = new Random();

    public Processo(int pid, int tempoExecucao) {
        this.pid = pid;
        this.tp = 0;
        this.cp = 1;
        this.ep = "Pronto";
        this.nes = 0;
        this.nCpu = 0;
        this.tempoExecucao = tempoExecucao;
    }

    public void atualizarContador() {
        tp++;
        cp = tp + 1;
    }

    public void executarQuantum() {
        int ciclosExecutados = 0;

        while (ciclosExecutados < QUANTUM && tp < tempoExecucao) {
            atualizarContador();
            ciclosExecutados++;

            // Checa chance de 1% para operação de E/S
            if (random.nextInt(100) < 1) { 
                nes++;
                trocaDeContexto("Bloqueado");
                return;
            }
        }

        nCpu++;

        if (tp >= tempoExecucao) {
            finalizarProcesso();
        } else {
            trocaDeContexto("Pronto");
        }
    }

    public void finalizarProcesso() {
        ep = "Finalizado";
        System.out.println("Processo " + pid + " terminou sua execução.");
        System.out.println("PID: " + pid);
        System.out.println("TP: " + tp);
        System.out.println("CP: " + cp);
        System.out.println("EP: " + ep);
        System.out.println("NES: " + nes);
        System.out.println("N_CPU: " + nCpu);
    }

    public void trocaDeContexto(String novoEstado) {
        salvarDados();
        String estadoAnterior = this.ep;
        this.ep = novoEstado;

        System.out.println("Processo " + pid + ": " + estadoAnterior + " >>> " + novoEstado);
        System.out.println("PID: " + pid);
        System.out.println("TP: " + tp);
        System.out.println("CP: " + cp);
        System.out.println("EP: " + ep);
        System.out.println("NES: " + nes);
        System.out.println("N_CPU: " + nCpu);
    }

    public void salvarDados() {
        String nomeArquivo = "processo_" + pid + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("PID: " + pid + "\n");
            writer.write("TP: " + tp + "\n");
            writer.write("CP: " + cp + "\n");
            writer.write("EP: " + ep + "\n");
            writer.write("NES: " + nes + "\n");
            writer.write("N_CPU: " + nCpu + "\n");
            System.out.println("Dados do processo " + pid + " salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados do processo " + pid + ": " + e.getMessage());
        }
    }

    public void carregarDados() {
        String nomeArquivo = "processo_" + pid + ".txt";
        try (Scanner scanner = new Scanner(new File(nomeArquivo))) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(": ");
                switch (partes[0]) {
                    case "PID":
                        this.pid = Integer.parseInt(partes[1]);
                        break;
                    case "TP":
                        this.tp = Integer.parseInt(partes[1]);
                        break;
                    case "CP":
                        this.cp = Integer.parseInt(partes[1]);
                        break;
                    case "EP":
                        this.ep = partes[1];
                        break;
                    case "NES":
                        this.nes = Integer.parseInt(partes[1]);
                        break;
                    case "N_CPU":
                        this.nCpu = Integer.parseInt(partes[1]);
                        break;
                }
            }
            System.out.println("Dados do processo " + pid + " carregados com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao carregar os dados do processo " + pid + ": Arquivo não encontrado.");
        }
    }

    public String getEstado() {
        return ep;
    }

    public boolean podeDesbloquear() {
        return random.nextInt(100) < 30;    //30% de chance
    } 
}

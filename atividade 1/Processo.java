import java.io.*;
import java.util.Scanner;

public class Processo {
    public int PID;
    public int TP;
    public int CP;
    public String EP;
    public int NES; 
    public int NCPU;

    //criar um construtor que pega de parametro pid e tempo de execução 
    public Processo(int PID, int TP) {
        this.PID = PID;
        this.TP = TP;
        this.CP = 0;
        this.EP = 0;
        this.NES = 0;
        this.N_CPU = 0;  
    }

    public trocaContexto() {

    }

        // Método para salvar os dados do processo em um arquivo TXT
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

    // Método para carregar os dados do processo a partir do arquivo TXT
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

    public void trocaDeContexto(String novoEstado) {
    salvarDados();

    // Atualiza o estado do processo para o novo estado
    String estadoAnterior = this.ep;
    this.ep = novoEstado;

    // Exibe a troca de contexto na tela
    System.out.println("Processo " + pid + ": " + estadoAnterior + " >>> " + novoEstado);
    System.out.println("PID: " + pid);
    System.out.println("TP: " + tp);
    System.out.println("CP: " + cp);
    System.out.println("EP: " + ep);
    System.out.println("NES: " + nes);
    System.out.println("N_CPU: " + nCpu);
}
}
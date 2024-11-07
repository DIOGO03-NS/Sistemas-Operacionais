public class Main {
    public static void main(String[] args) {
        GerenciadorDeProcessos gerenciador = new GerenciadorDeProcessos();

        gerenciador.adicionarProcesso(new Processo(0, 10000));
        gerenciador.adicionarProcesso(new Processo(1, 5000));
        gerenciador.adicionarProcesso(new Processo(2, 7000));
        gerenciador.adicionarProcesso(new Processo(3, 3000));
        gerenciador.adicionarProcesso(new Processo(4, 3000));
        gerenciador.adicionarProcesso(new Processo(5, 8000));
        gerenciador.adicionarProcesso(new Processo(6, 2000));
        gerenciador.adicionarProcesso(new Processo(7, 5000));
        gerenciador.adicionarProcesso(new Processo(8, 4000));
        gerenciador.adicionarProcesso(new Processo(9, 10000));

        gerenciador.iniciarExecucao();
    }
}

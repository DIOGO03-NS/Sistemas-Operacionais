public class Processo {
    private String id;
    private int tempoChegada;
    private int tempoExecucao;
    private int prioridade;
    private int tempoEspera;
    private int tempoRetorno;

    public Processo(String id, int tempoChegada, int tempoExecucao, int prioridade) {
        this.id = id;
        this.tempoChegada = tempoChegada;
        this.tempoExecucao = tempoExecucao;
        this.prioridade = prioridade;
        this.tempoEspera = 0;
        this.tempoRetorno = 0;
    }

    public String getId() {
        return id;
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public int getTempoExecucao() {
        return tempoExecucao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public int getTempoRetorno() {
        return tempoRetorno;
    }

    public void setTempoRetorno(int tempoRetorno) {
        this.tempoRetorno = tempoRetorno;
    }

    @Override
    public String toString() {
        return "Processo{" +
                "id='" + id + '\'' +
                ", tempoChegada=" + tempoChegada +
                ", tempoExecucao=" + tempoExecucao +
                ", prioridade=" + prioridade +
                ", tempoEspera=" + tempoEspera +
                ", tempoRetorno=" + tempoRetorno +
                '}';
    }

    public void setTempoExecucao(int i) {
        this.tempoExecucao = i;
    }
}
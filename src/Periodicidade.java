import java.util.Scanner;

enum Periodicidade {
    ANUAL(1, "Anual"),
    MENSAL(2, "Mensal"),
    DIARIA(3, "Diária"),
    NENHUMA(0, "Nenhuma");

    private final int valor;
    private final String descricao;

    Periodicidade(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() { return valor; }
    public String getDescricao() { return descricao; }

    // Método utilitário para converter int em Enum
    public static Periodicidade deInt(int i) {
        for (Periodicidade p : Periodicidade.values()) {
            if (p.getValor() == i) return p;
        }
        return NENHUMA;
    }

    public static void descrever () {
        for (Periodicidade p : Periodicidade.values()) {
            if (p != Periodicidade.NENHUMA) {
                System.out.println(p.getValor() + " - " + p.getDescricao());
            }
        }
    }
}

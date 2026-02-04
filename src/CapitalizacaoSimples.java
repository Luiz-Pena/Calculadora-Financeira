import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class CapitalizacaoSimples implements Calculos{
    private double valor_futuro;
    private double taxa;
    private double tempo;
    private double valor_presente;
    private double taxa_efetiva;
    private double taxa_comercial;
    Scanner scanner = new Scanner(System.in);

    @Override
    public void menu(){
        Map<Integer, Supplier<Object>> acoes = getMap();

        System.out.println("----------------------");
        System.out.println("Digite uma opcao");
        System.out.println("1 - Calcular valor presente");
        System.out.println("2 - Calcular valor futuro");
        System.out.println("3 - Calcular juros");
        System.out.println("4 - Calcular taxa");
        System.out.println("5 - Calcular tempo");
        System.out.println("6 - Calcular taxa efetiva");
        System.out.println("7 - Calcular taxa comercial");
        System.out.println("----------------------");

        int escolha = scanner.nextInt();
        if (acoes.containsKey(escolha)) {
            System.out.println(acoes.get(escolha).get());
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private Map<Integer, Supplier<Object>> getMap() {
        Map<Integer, Supplier<Object>> acoes = new HashMap<>();

        acoes.put(1, () -> "Valor presente: " + getValor_presente());
        acoes.put(2, () -> "Valor futuro: " + getValor_futuro());
        acoes.put(3, () -> "Juros: " + getJuros());
        acoes.put(4, () -> "Taxa: " + getTaxa() * 100);
        acoes.put(5, () -> "Tempo: " + getTempo());
        acoes.put(6, () -> "Taxa efetiva: " + getTaxa_efetiva() * 100);
        acoes.put(7, () -> "Taxa comercial: " + getTaxa_comercial() * 100);
        return acoes;
    }

    public double getTaxa_efetiva(){
        setTaxa_comercial();
        setTempo(false);

        return 100 * (taxa_comercial / (1 - taxa_comercial * tempo));
    }

    public double getTaxa_comercial(){
        setTaxa_efetiva();
        setTempo(false);

        return 100 * (taxa_efetiva / (1 + taxa_efetiva * tempo));
    }

    public double getValor_presente() {
        setValor_futuro();
        setTaxa(true);
        setTempo(true);

        return valor_futuro / (1 + taxa * tempo);
    }

    public double getValor_futuro() {
        setValor_presente();
        setTaxa(true);
        setTempo(true);

        return valor_presente * (1 + taxa * tempo);
    }

    public double getJuros() {
        setValor_presente();
        setTaxa(true);
        setTempo(true);

        return valor_presente * taxa * tempo;
    }

    public double getTaxa() {
        setValor_futuro();
        setValor_presente();
        setTempo(false);

        return 100 * ((valor_futuro / valor_presente - 1) / tempo);
    }

    public double getTempo() {
        setValor_presente();
        setValor_futuro();
        setTaxa(false);

        return (valor_futuro / valor_presente - 1) / taxa;
    }

    public void setTempo(Boolean dif_tempo) {
        System.out.println("Digite o tempo:");
        double tempo = scanner.nextDouble();

        if (dif_tempo) {
            System.out.println("1 - Ano");
            System.out.println("2 - Mes");
            System.out.println("3 - Dia");
            System.out.println("O tempo eh:");

            int opcao = scanner.nextInt();

            if (opcao < 1 || opcao > 3) {
                System.out.println("Opcao invalida!");
                return;
            }

            switch (opcao) {
                case 1:
                    tempo *= 360;
                    break;
                case 2:
                    tempo *= 30;
                    break;
                case 3:
                    break;
            }
        }

        this.tempo = tempo;

    }

    public void setValor_presente() {
        System.out.print("Digite o valor presente (Capital):");
        valor_presente = scanner.nextDouble();
    }

    public void setTaxa(Boolean dif_tempo) {
        System.out.print("Digite a taxa:");
        double taxa = scanner.nextDouble();

        if (dif_tempo) {
            System.out.println("1 - Anual");
            System.out.println("2 - Mensal");
            System.out.println("3 - Diaria");
            System.out.println("A taxa eh:");

            int opcao = scanner.nextInt();

            if  (opcao < 1 || opcao > 3) {
                System.out.println("Opcao invalida!");
                return;
            }

            switch (opcao) {
                case 1:  taxa /= 360; break;
                case 2:  taxa /= 30; break;
                case 3:  break;
            }
        }

        this.taxa = taxa/100;

    }

    public void setValor_futuro() {
        System.out.print("Digite o valor futuro (Montante):");
        valor_futuro = scanner.nextDouble();
    }

    public void setTaxa_comercial() {
        System.out.print("Digite o taxa desconto comercial:");
        taxa_comercial = scanner.nextDouble() / 100;
    }

    public void setTaxa_efetiva() {
        System.out.print("Digite o taxa desconto efetiva:");
        taxa_efetiva =  scanner.nextDouble() / 100;
    }
}

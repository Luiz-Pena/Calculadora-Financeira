import java.util.Scanner;

public class CapitalizacaoSimples implements Calculos{
    private double valor_futuro;
    private double taxa;
    private double tempo;
    private double valor_presente;
    private double taxa_efetiva;
    private double taxa_comercial;
    Scanner scanner = new Scanner(System.in);

    public void menu(){
        System.out.println("Digite uma opcao");
        System.out.println("1 - Calcular valor futuro");
        System.out.println("2 - Calcular valor presente");
        System.out.println("3 - Calcular tempo");
        System.out.println("4 - Calcular taxa");
        System.out.println("5 - Calcular juros");
        System.out.println("6 - Calcular taxa efetiva");
        System.out.println("7 - Calcular taxa comercial");

        double resultado;

        switch (scanner.nextInt()){
            case 1:
                resultado = getValor_futuro();
                System.out.println("Valor futuro: " + resultado);
                break;

            case 2:
                resultado = getValor_presente();
                System.out.println("Valor presente: " + resultado);
                break;

            case 3:
                 resultado = getTempo();
                 System.out.println("Tempo: " + resultado);
                 break;

            case 4:
                resultado = getTaxa();
                System.out.println("Taxa: " + resultado);
                break;

            case 5:
                resultado = getJuros();
                System.out.println("Juros: " + resultado);
                break;

            case 6:
                resultado = getTaxa_efetiva();
                System.out.println("Taxa efetiva: " + resultado);
                break;

            case 7:
                resultado = getTaxa_comercial();
                System.out.println("Taxa comercial: " + resultado);
                break;

            default:
                System.out.println("Opcao invalida!");
                break;
        }
    }

    public double getTaxa_efetiva(){
        setTaxa_comercial();
        setTempo();

        return taxa_comercial / (1 - taxa_comercial * tempo);
    }

    public double getTaxa_comercial(){
        setTaxa_efetiva();
        setTempo();

        return taxa_efetiva / (1 + taxa_efetiva * tempo);
    }

    public double getValor_presente() {
        setValor_futuro();
        setTaxa();
        setTempo();

        return valor_futuro / (1 + taxa * tempo);
    }

    public double getValor_futuro() {
        setValor_presente();
        setTaxa();
        setTempo();

        return valor_presente * (1 + taxa * tempo);
    }

    public double getJuros() {
        setValor_presente();
        setTaxa();
        setTempo();

        return valor_presente * taxa * tempo;
    }

    public double getTaxa() {
        setValor_futuro();
        setValor_presente();
        setTempo();

        return (valor_futuro/valor_presente - 1)/tempo;
    }

    public double getTempo() {
        setValor_presente();
        setValor_futuro();
        setTaxa();

        return (valor_futuro/valor_presente - 1)/taxa;
    }

    public void setTempo() {
        System.out.println("O tempo eh:");
        System.out.println("1 - Ano");
        System.out.println("2 - Mes");
        System.out.println("3 - Dia");

        int opcao = scanner.nextInt();

        if (opcao < 1 || opcao > 3) {
            System.out.println("Opcao invalida!");
            return;
        }

        System.out.println("Digite o tempo:");
        double tempo = scanner.nextDouble();

        switch (opcao) {
            case 1: this.tempo = tempo/360; break;
            case 2: this.tempo = tempo/30; break;
            case 3: this.tempo = tempo; break;
        }
    }

    public void setValor_presente() {
        System.out.println("Digite o valor presente (Capital):");
        valor_presente = scanner.nextDouble();
    }

    public void setTaxa() {
        System.out.println("A taxa eh:");
        System.out.println("1 - Anual");
        System.out.println("2 - Mensal");
        System.out.println("3 - Diaria");

        int opcao = scanner.nextInt();

        if  (opcao < 1 || opcao > 3) {
            System.out.println("Opcao invalida!");
            return;
        }

        System.out.println("Digite a taxa:");
        double taxa = scanner.nextDouble();

        switch (opcao) {
            case 1: this.taxa = taxa/360; break;
            case 2: this.taxa = taxa/30; break;
            case 3: this.taxa = taxa; break;
        }
    }

    public void setValor_futuro() {
        System.out.println("Digite o valor futuro (Montante):");
        valor_futuro = scanner.nextDouble();
    }

    public void setTaxa_comercial() {
        System.out.println("Digite o taxa desconto comercial:");
        taxa_comercial = scanner.nextDouble();;
    }

    public void setTaxa_efetiva() {
        System.out.println("Digite o taxa desconto efetiva:");
        taxa_efetiva =  scanner.nextDouble();
    }
}

import java.util.Scanner;

public class CapitalizacaoComposta implements Calculos{
    private double valor_futuro;
    private double valor_presente;
    private double taxa;
    private double tempo;
    private double proporcionalidade;
    private double taxa_nominal;
    private double taxa_efetiva;
    private double tempo_desejado;
    Scanner scanner = new Scanner(System.in);

    @Override
    public void menu() {
        System.out.println("Digite uma opcao: ");
        System.out.println("1 - Calcular valor presente (capital) ");
        System.out.println("2 - Calcular valor futuro (montante) ");
        System.out.println("3 - Calcular juros ");
        System.out.println("4 - Calcular taxa ");
        System.out.println("5 - Calcular tempo");
        System.out.println("6 - Calcular taxa proporcional (efetiva) ");
        System.out.println("7 - Calcular taxa nominal ");
        System.out.println("8 - Calcular taxa equivalente");

        double resultado;

        switch (scanner.nextInt()){
            case 1:
                resultado = getValor_presente();
                System.out.println("Valor presente: " + resultado);
                break;

            case 2:
                resultado = getValor_futuro();
                System.out.println("Valor futuro: " + resultado);
                break;

            case 3:
                resultado = getJuros();
                System.out.println("Juros: " + resultado);
                break;

            case 4:
                resultado = getTaxa();
                System.out.println("Taxa: " + resultado);
                break;

            case 5:
                resultado = getTempo();
                System.out.println("Tempo: " + resultado);
                break;

            case 6:
                resultado = getTaxa_efetiva();
                System.out.println("Taxa efetiva: " + resultado);
                break;

            case 7:
                resultado = getTaxa_nominal();
                System.out.println("Taxa nominal: " + resultado);
                break;

            case 8:
                resultado = getTaxa_equivalente();
                System.out.println("Taxa equivalente: " + resultado);
                break;
        }
    }

    public double getValor_presente() {
        setValor_futuro();
        setTempo(setTaxa(true));

        return valor_futuro / Math.pow((1 + taxa), tempo);
    }

    public double getValor_futuro() {
        setValor_presente();
        setTempo(setTaxa(true));

        return valor_presente * Math.pow((1 + taxa), tempo);
    }

    public double getJuros() {
        setValor_presente();
        setTempo(setTaxa(true));

        return valor_presente * (Math.pow((1 + taxa), tempo) - 1);
    }

    public double getTaxa() {
        setValor_presente();
        setValor_futuro();
        setTempo(0);

        return Math.pow((valor_futuro / valor_presente), 1 / tempo) - 1;
    }

    public double getTempo() {
        setValor_presente();
        setValor_futuro();
        setTaxa(false);

        return Math.log10(valor_futuro / valor_presente) / Math.log10(1 + taxa);
    }

    public double getTaxa_efetiva(){
        setTaxa_nominal();
        setProporcionalidade();

        return taxa_nominal * proporcionalidade;
    }

    public double getTaxa_nominal(){
        setTaxa_efetiva();
        setProporcionalidade();

        return taxa_efetiva / proporcionalidade;
    }

    public double getTaxa_equivalente(){
        setTempo_desejado(setTaxa(true));

        return Math.pow(1 + taxa, tempo_desejado) - 1;
    }

    public void setTaxa_efetiva(){
        System.out.print("Digite a taxa efetiva: ");
        this.taxa_efetiva = scanner.nextDouble();
    }

    public void setTaxa_nominal(){
        System.out.print("Digite a taxa nominal: ");
        this.taxa_nominal = scanner.nextDouble();
    }

    public void setProporcionalidade(){
        System.out.print("Opcoes: ");
        System.out.println("1 - Semestral");
        System.out.println("2 - Trimestral");
        System.out.println("3 - Bimestral");
        System.out.println("4 - Mensal");

        switch (scanner.nextInt()){
            case 1: this.proporcionalidade = 2; break;
            case 2: this.proporcionalidade = 4; break;
            case 3: this.proporcionalidade = 3; break;
            case 4: this.proporcionalidade = 6; break;
        }
    }

    public void setValor_futuro(){
        System.out.print("Digite o valor Futuro: ");
        this.valor_futuro = scanner.nextDouble();
    }

    public void setValor_presente(){
        System.out.print("Digite o valor Presente: ");
        this.valor_presente = scanner.nextDouble();
    }

    public int setTaxa(Boolean dif_tempo){
        System.out.print("Digite o taxa: ");
        this.taxa = scanner.nextDouble() / 100;

        if (dif_tempo){
            System.out.println("A taxa eh: ");
            System.out.println("1 - Anual");
            System.out.println("2 - Mensal");
            System.out.println("3 - Diaria");

            return scanner.nextInt();
        }

        return 0;
    }

    public void setTempo(int tempo_taxa){
        System.out.print("Digite o tempo: ");
        double tempo = scanner.nextDouble();

        if (tempo_taxa > 0) {
            System.out.println("O tempo eh:");
            System.out.println("1 - Anual");
            System.out.println("2 - Mensal");
            System.out.println("3 - Diaria");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    if (tempo_taxa == 2) {
                        tempo *= 12;
                    } else if (tempo_taxa == 3) {
                        tempo *= 360;
                    }
                    break;

                case 2:
                    if (tempo_taxa == 1) {
                        tempo /= 12;
                    } else if (tempo_taxa == 3) {
                        tempo *= 30;
                    }
                    break;

                case 3:
                    if (tempo_taxa == 2) {
                        tempo /= 30;
                    } else if (tempo_taxa == 3) {
                        tempo /= 360;
                    }
                    break;
            }
        }

        this.tempo = tempo;
    }

    public void setTempo_desejado(int tempo_taxa){
        System.out.print("Digite o tempo desejado: ");
        System.out.println("1 - Anual");
        System.out.println("2 - Mensal");
        System.out.println("3 - Diaria");

        int opcao = scanner.nextInt();

        switch (tempo_taxa) {
            case 1:
                if (opcao == 2) {
                    tempo_desejado = 1/12.;
                } else if (opcao == 3) {
                    tempo_desejado = 1/360.;
                }
                break;

            case 2:
                if (opcao == 1) {
                    tempo_desejado = 12;
                } else if (opcao == 3) {
                    tempo_desejado = 1/30.;
                }
                break;

            case 3:
                if (opcao == 2) {
                    tempo_desejado = 30;
                } else if (opcao == 3) {
                    tempo_desejado = 360;
                }
        }
    }
}

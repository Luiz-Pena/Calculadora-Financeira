import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class CapitalizacaoComposta implements Calculos {
    private double valor_futuro;
    private double valor_presente;
    private double taxa;
    private double tempo;
    private double capitalizacao;
    private double taxa_nominal;
    private double taxa_efetiva;
    private double tempo_desejado;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void menu() {
        Map<Integer, Supplier<Object>> acoes = getMap();

        System.out.println("----------------------");
        System.out.println("Digite uma opcao: ");
        System.out.println("1 - Calcular valor presente (capital) ");
        System.out.println("2 - Calcular valor futuro (montante) ");
        System.out.println("3 - Calcular juros ");
        System.out.println("4 - Calcular taxa ");
        System.out.println("5 - Calcular tempo");
        System.out.println("6 - Calcular taxa proporcional (efetiva) ");
        System.out.println("7 - Calcular taxa nominal ");
        System.out.println("8 - Calcular taxa equivalente");
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
        acoes.put(4, () -> "Taxa: " + getTaxa());
        acoes.put(5, () -> "Tempo: " + getTempo());
        acoes.put(6, () -> "Taxa efetiva: " + getTaxa_efetiva());
        acoes.put(7, () -> "Taxa nominal: " + getTaxa_nominal());
        acoes.put(8, () -> "Taxa equivalente: " + getTaxa_equivalente());
        return acoes;
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
        setTempo(Periodicidade.deInt(0));

        return 100 * Math.pow((valor_futuro / valor_presente), 1 / tempo) - 1;
    }

    public double getTempo() {
        setValor_presente();
        setValor_futuro();
        setTaxa(false);

        return Math.log10(valor_futuro / valor_presente) / Math.log10(1 + taxa);
    }

    public double getTaxa_efetiva() {
        setTaxa_nominal();
        setCapitalizacao();

        return taxa_nominal * capitalizacao;
    }

    public double getTaxa_nominal() {
        setTaxa_efetiva();
        setCapitalizacao();

        return taxa_efetiva / capitalizacao;
    }

    public double getTaxa_equivalente() {
        setTempo_desejado(setTaxa(true));

        return 100 * Math.pow(1 + taxa, tempo_desejado) - 1;
    }

    public void setTaxa_efetiva() {
        System.out.print("Digite a taxa efetiva: ");
        this.taxa_efetiva = scanner.nextDouble();
    }

    public void setTaxa_nominal() {
        System.out.print("Digite a taxa nominal: ");
        this.taxa_nominal = scanner.nextDouble();
    }

    public void setCapitalizacao() {
        System.out.println("1 - Semestral");
        System.out.println("2 - Quadrimestral");
        System.out.println("3 - Trimestral");
        System.out.println("4 - Bimestral");
        System.out.println("5 - Mensal");
        System.out.print("Capitalizacao: ");

        Integer[] resultado = new Integer[6];
        resultado[1] = 2;
        resultado[2] = 3;
        resultado[3] = 4;
        resultado[4] = 6;
        resultado[5] = 12;

        int input = scanner.nextInt();

        if (input > 0 && input < 6) {
            this.capitalizacao = resultado[input];
        }
    }

    public void setValor_futuro() {
        System.out.print("Digite o valor Futuro: ");
        this.valor_futuro = scanner.nextDouble();
    }

    public void setValor_presente() {
        System.out.print("Digite o valor Presente: ");
        this.valor_presente = scanner.nextDouble();
    }

    public Periodicidade setTaxa(Boolean dif_tempo) {
        System.out.print("Digite a taxa: ");
        this.taxa = scanner.nextDouble() / 100;

        if (dif_tempo) {
            Periodicidade.descrever();
            System.out.print("Escolha a periodicidade da taxa: ");

            return Periodicidade.deInt(scanner.nextInt());
        }

        return Periodicidade.NENHUMA;
    }

    public void setTempo(Periodicidade tempo_taxa) {
        System.out.print("Digite o tempo (valor numérico): ");
        double tempoInformado = scanner.nextDouble();

        if (tempo_taxa != Periodicidade.NENHUMA) {
            Periodicidade.descrever();
            System.out.print("Digite a unidade desse tempo: ");
            Periodicidade tempoEntrada = Periodicidade.deInt(scanner.nextInt());

            switch (tempoEntrada) {
                case ANUAL:
                    if (tempo_taxa == Periodicidade.MENSAL) tempoInformado *= 12;
                    else if (tempo_taxa == Periodicidade.DIARIA) tempoInformado *= 360;
                    break;

                case MENSAL:
                    if (tempo_taxa == Periodicidade.ANUAL) tempoInformado /= 12;
                    else if (tempo_taxa == Periodicidade.DIARIA) tempoInformado *= 30;
                    break;

                case DIARIA:
                    if (tempo_taxa == Periodicidade.MENSAL) tempoInformado /= 30;
                    else if (tempo_taxa == Periodicidade.ANUAL) tempoInformado /= 360;
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        this.tempo = tempoInformado;
    }

    public void setTempo_desejado(Periodicidade tempo_taxa) {
        Periodicidade.descrever();
        System.out.print("Digite o tempo desejado (unidade de destino): ");

        Periodicidade opcao = Periodicidade.deInt(scanner.nextInt());

        switch (tempo_taxa) {
            case ANUAL:
                if (opcao == Periodicidade.MENSAL) {
                    this.tempo_desejado = 1 / 12.0;
                } else if (opcao == Periodicidade.DIARIA) {
                    this.tempo_desejado = 1 / 360.0;
                }
                break;

            case MENSAL:
                if (opcao == Periodicidade.ANUAL) {
                    this.tempo_desejado = 12.0;
                } else if (opcao == Periodicidade.DIARIA) {
                    this.tempo_desejado = 1 / 30.0;
                }
                break;

            case DIARIA:
                if (opcao == Periodicidade.MENSAL) {
                    this.tempo_desejado = 30.0;
                } else if (opcao == Periodicidade.ANUAL) {
                    this.tempo_desejado = 360.0;
                }
                break;

            default:
                this.tempo_desejado = 1.0;
                break;
        }
    }
}
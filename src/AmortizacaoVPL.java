import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class AmortizacaoVPL implements Calculos {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void menu() {
        Map<Integer, Supplier<Object>> acoes = new HashMap<>();
        
        acoes.put(1, () -> { calcularSAC(); return "Fim da tabela SAC."; });
        acoes.put(2, () -> "VPL: " + getVPL());

        System.out.println("----------------------");
        System.out.println("3ª Parte - Amortização e VPL");
        System.out.println("1 - Gerar Tabela SAC");
        System.out.println("2 - Calcular VPL");
        System.out.println("----------------------");

        int escolha = scanner.nextInt();
        if (acoes.containsKey(escolha)) {
            System.out.println(acoes.get(escolha).get());
        } else {
            System.out.println("Opção inválida!");
        }
    }

    public void calcularSAC() {
        System.out.print("Valor do Financiamento (VP): ");
        double vp = scanner.nextDouble();
        System.out.print("Taxa (i) em %: ");
        double i = scanner.nextDouble() / 100;
        System.out.print("Tempo (n): ");
        int n = scanner.nextInt();

        double amortizacao = vp / n;
        double saldoDevedor = vp;

        System.out.printf("%-5s | %-12s | %-10s | %-12s | %-15s%n", "Mês", "Prestação", "Juros", "Amortização", "Saldo Devedor");
        
        for (int t = 1; t <= n; t++) {
            double juros = saldoDevedor * i;
            double prestacao = amortizacao + juros;
            saldoDevedor -= amortizacao;

            System.out.printf("%-5d | %-12.2f | %-10.2f | %-12.2f | %-15.2f%n", t, prestacao, juros, amortizacao, Math.abs(saldoDevedor));
        }
    }

    public double getVPL() {
        System.out.print("Investimento Inicial: ");
        double investimento = scanner.nextDouble();
        System.out.print("Taxa de Desconto (i) em %: ");
        double i = scanner.nextDouble() / 100;
        System.out.print("Número de períodos (n): ");
        int n = scanner.nextInt();

        double vpl = -investimento;

        for (int t = 1; t <= n; t++) {
            System.out.print("Fluxo de Caixa do período " + t + ": ");
            double fc = scanner.nextDouble();
            vpl += fc / Math.pow(1 + i, t);
        }

        System.out.print("Existe valor residual? (1-Sim / 0-Não): ");
        if (scanner.nextInt() == 1) {
            System.out.print("Valor Residual: ");
            double vr = scanner.nextDouble();
            System.out.print("Período do valor residual: ");
            int tempoVr = scanner.nextInt();
            vpl += vr / Math.pow(1 + i, tempoVr);
        }

        return vpl;
    }
}
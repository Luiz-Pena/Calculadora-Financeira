import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Calculos[] opcoes = new Calculos[4];
        int ops;
        Scanner sc = new Scanner(System.in);

        opcoes[1] = new CapitalizacaoSimples();
        opcoes[2] = new CapitalizacaoComposta();

        do {
            System.out.println("==============================");
            System.out.println("Menu: ");
            System.out.println("1 - Capitalizao Simples: ");
            System.out.println("2 - Capitalizacao Composta: ");
            System.out.println("4 - Sair");
            System.out.println("==============================");

            ops = sc.nextInt();

            switch (ops) {
                case 1:
                    opcoes[1].menu();
                    break;

                case 2:
                    opcoes[2].menu();
                    break;
            }
        } while (ops != 4);
    }
}
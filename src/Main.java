import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculos[] opcoes = new Calculos[3];
        int ops;
        opcoes[1] = new CapitalizacaoSimples();

        do {
            System.out.println("Menu: ");
            System.out.println("1 - Capitalizao Simples: ");
            System.out.println("4 - Sair");

            ops = sc.nextInt();
            switch (ops) {
                case 1:
                    opcoes[1].menu();
                    break;
            }
        } while (ops != 4);
    }
}
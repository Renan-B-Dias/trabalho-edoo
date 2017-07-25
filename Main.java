import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

        System.out.println("Rabin Karp Algorithm Test");
        System.out.println("Digite o texto");

        String text = sc.nextLine();

        System.out.println("Digite o padrão a ser buscado");
        String pattern = sc.nextLine();
        System.out.println("Resultados: ");

        RabinKarp rk = new RabinKarp(text, pattern);
	}

}
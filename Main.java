import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

        System.out.println("Rabin Karp Algorithm Test");
        System.out.println("Digite o texto");

        String text = sc.nextLine();

        RabinKarp rbkp = new RabinKarp(text);

        String pattern = "";

        while(!pattern.equals("-11")) {

        	System.out.println("Digite o padrão a ser buscado");
        	pattern = sc.nextLine();

        	if(pattern.equals("-11"))
        		break;

        	int index = rbkp.lookFor(pattern);

        	if(index < 0)
        		System.out.println("Não encontrado");
        	else
        		System.out.println("Index na posicao: " + index);

        	System.out.println("Difgite -11 para fechar");
        }

        System.out.println("Fechado.");
	}

}
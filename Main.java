import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class Main {

  public static void main(String args[]) {

    if(args.length >= 2) {
      String filePath = args[0];
      String pattern = args[1];

      File file = new File(filePath);

      try {

        if(!file.exists())
          throw new FileNotFoundException("Arquivo nao encontrado");

        Scanner streamFile = new Scanner(file);

        String wholeText = "";

        while(streamFile.hasNext()) {
          wholeText += streamFile.nextLine();
        }

        //System.out.println(wholeText);

        RabinKarp rbkp = new RabinKarp(wholeText);

        int pos = rbkp.lookFor(pattern);

        if(pos >= 0) {
          System.out.println("Encontrado na posicao: " + pos);
        }
        else {
          System.out.println("Nao encontrado");
        }

      } catch(FileNotFoundException e) {
        System.out.println(e.getMessage());
        System.exit(-1);
      }
      catch(Exception e) {
        System.out.println("Unexpected exception");
        System.exit(-1);
      }
    } 
    else {
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

        System.out.println("Difgite -11 para fechar\n\n");
      }

      System.out.println("Fechado.");
    }
  }
}
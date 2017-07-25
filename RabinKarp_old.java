import java.util.Random;
import java.math.BigInteger;

public class RabinKarp {

    // String do padrao
    private String pattern; 

    // Hash do padrao
    private long patternHash;

    // Tamanho do padrao a ser achado
    private int patternSize;

    // Numero primo 'grande'
    private long primeNumber;

    // radix
    private int radix;

    // radix^(patternLength-1) % primeNumber
    private long RM;
 
    // Contrutor
    public RabinKarp(String txt, String pattern)  {
        this.pattern = pattern;
        patternSize = pattern.length();
        primeNumber = longRandomPrime();

        /** precompute radix^(M-1) % primeNumber for use in removing leading digit **/
        RM = 1;
        for (int i = 1; i <= patternSize-1; i++)
           RM = (radix * RM) % primeNumber;

        patternHash = hash(pattern, patternSize);

        int pos = search(txt);

        if (pos == -1 || pos >= txt.length())
            System.out.println("Padrão não encontrado");
        else
            System.out.println("Padrão achado, inicio no index: "+ pos);
    }

    // Calcula o Hash
    private long hash(String key, int patternSize) { 
        long hash = 0; 
        for (int i = 0; i < patternSize; i++) 
            hash = (radix * hash + key.charAt(i)) % primeNumber; 
        return hash; 
    }

    // Metodo para verificar se padrão cabe no texto
    private boolean check(String txt, int i) {
        for (int j = 0; j < patternSize; j++) 
            if (pattern.charAt(j) != txt.charAt(i + j)) 
                return false; 
        return true;
    }

    /**
    * Metodo que procura uma 'match'
    * Retorna -1 se não achar nem um match
    * Retorna a posição que começa o padrão encontrado
    * ou retorna o tamanho do texto (padrão inválido)
    */
    private int search(String txt) {
        int textSize = txt.length(); 

        // Se o texto for menor do que o padrão simplesmente retorna o tamanho do texto (tratamos la em cima).
        if (textSize < patternSize)
            return textSize; // Change to 'return -1'

        // Hash do texto
        long txtHash = hash(txt, patternSize); 

        // Se hash do padrão e do texto baterem então o texto é igual ao padrão. Retorna 0
        if ((patternHash == txtHash) && check(txt, 0)) // check é inutil?
            return 0;

        /** check for hash match. if hash match then check for exact match**/
        // Procura por uma match do hash, se existe então procura o index
        for (int i = patternSize; i < textSize; i++) {

            // Remove leading digit, add trailing digit, check for match. 
            txtHash = (txtHash + primeNumber - RM * txt.charAt(i - patternSize) % primeNumber) % primeNumber; 
            txtHash = (txtHash * radix + txt.charAt(i)) % primeNumber; 

            // match
            int offset = i - patternSize + 1;
            if ((patternHash == txtHash) && check(txt, offset))
                return offset;
        }
        
        // Nao encontrou
        return -1;
    }

    /**
    * Gera um numero primo de 31 bits
    */
    private static long longRandomPrime() {
        return BigInteger.probablePrime(31, new Random()).longValue();
    }
}
import java.util.Random;
import java.math.BigInteger;

public class RabinKarp {

  // Numero primo 'grande'
  private long primeNumber;
  // radix
  private int radix;
  // Texto inteiro
  private String txt;

  // Construtor
  public RabinKarp(String txt) {
    this.txt = txt;
    this.primeNumber = longRandomPrime();
    this.radix = 52;
  }

  public int lookFor(String pattern) {
    int txtSize = this.txt.length();
    int patternSize = pattern.length();

    if(txtSize < patternSize)
      return -1;

    long txtHash = hash(this.txt, patternSize);
    long patternHash = hash(pattern, patternSize);
    long RM = calcRM(patternSize);

    if((patternHash == txtHash) && check(txt, 0, pattern))
      return 0;

    for(int i = patternSize; i < txtSize; i++) {
      txtHash = (txtHash + primeNumber - RM * txt.charAt(i - patternSize) % primeNumber) % primeNumber;
      txtHash = (txtHash * radix + txt.charAt(i)) % primeNumber;

      int offset = i - patternSize + 1;
      if((patternHash == txtHash) && check(txt, offset, pattern))
        return offset;
    }
    return -1;
  }

  private long calcRM(int patternSize) {
    long RM = 1;
    for (int i = 1; i <= patternSize-1; i++)
      RM = (radix * RM) % primeNumber;
    return RM;
  }

  // Calcula o Hash
  private long hash(String key, int patternSize) { 
    long hash = 0; 
    for (int i = 0; i < patternSize; i++) 
      hash = (radix * hash + key.charAt(i)) % primeNumber; 
    return hash; 
  }

  // Metodo para verificar se padrão cabe no texto
  private boolean check(String txt, int i, String pattern) {
    int patternSize = pattern.length();
    for (int j = 0; j < patternSize; j++) 
      if (pattern.charAt(j) != txt.charAt(i + j)) 
        return false; 
    return true;
  }

  /**
  * Gera um numero primo de 31 bits
  */
  private static long longRandomPrime() {
    return BigInteger.probablePrime(31, new Random()).longValue();
  }
}
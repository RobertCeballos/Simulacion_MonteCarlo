/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package the_codificador;

import java.lang.*;
import java.math.*;

/**
 * <p>Title: Codify</p>
 * @author Juan Ant. Recio
 * @version 1.0
 */
public class Codify {

  /**
   * Intenta averiguar si un numero es primo por el metodo de Fermat
   * @param n Numero
   * @param t Factor Seguridad
   * @return Booleano indicando si es primo
   */
  public static boolean primoFermat(int n, int t)
  {
    int a,b;
    boolean primo = true;
    int i = 1;
    // bucle principal. t vueltas
    while(primo && (i<t))
    {
      a =(int) Math.floor(2+(Math.random()* (n-2))); // a = aleatorio 2..n-1
      b =(int) Codify.expRapida(a,n-1,n);        // b = a^n-1 mod n
      i++;
      primo = (b==1);       // si b = 1 entonces es primo.
    }
    return primo;
  }


  /**
   * Intenta averiguar si un numero es primo por el metodo de Miller-Rabin
   * @param n Numero
   * @param t Factor Seguridad
   * @return Booleano indicando si es primo
   */
  public static boolean primoMR(int n, int t)
  {
    boolean compuesto = true;
    int m=n-1; int s=0;
    int r,a,b;
    while ((m % 2) == 0)
    {
      s=s+1; m=m/2;   // s = veces que 2 divide a n-1
    }
    r=m;
    int i=1;
    while (compuesto && (i<t))
    {
      a=(int) Math.floor(2+(Math.random()* (n-2))); //a= aleatorio 2..n-1
      if (Codify.mcd(a,n)!=1)
        i=t; //acabamos -> es compuesto
      else
      {
        b=(int) Codify.expRapida(a,r,n);       // b = a^r mod n
        if ((b!=1) && (b!=-1) )
          for (int j=0; j<=s-1; j++)
            b=(int) Codify.expRapida(b,2,n);   // b = b^2 mod n
        compuesto = (b!=1) && (b!=-1);             // sigue sin ser primo
      }
      i++;
    }
    return !compuesto;
  }

  /**
   * Halla el maximo comun divisor de dos numeros
   */
  public static double mcd(double x,double y )
  {
    double a,b;
    a = x;
    b = y;
    if ((a<1) || (b<0))
      return 1;
    else {
    while( a != b )
    {
      if( a < b )
      {
        b = b - a;
      } else {
        a = a - b;
      }
    }
    return(a);
    }
  }

  /**
   * Realiza la operacion: num^b mod n  de forma eficiente
   */
  public static double expRapida(double num,double b,double n)
  {
    double z, x, resul;
    z = b;
    x = num;
    resul = 1;

    while (z>0)
    {
      if ((z%2)== 1)
      {
        resul = (resul*x) % n;
        z--;
      }else
      {
        x = (x*x) % n;
        z = java.lang.Math.floor(z/2);
      }
    }
    return resul;
  }

  /** Lista de primos necesaria para factorizar*/
  protected static int primosB[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113,127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};

  /**
   * Devuelve primo^parteEntera(log(n)). Como la clase BigInteger no dispone de
   * funciones para realizar el logaritmo, hay que ir multiplicando hasta llegar a n
   * @param primo
   * @param n
   * @return primo^parteEntera(log(n))
   */
  private static BigInteger primoElevado(BigInteger primo, BigInteger n)
  {
    BigInteger acum = new BigInteger("1");
    while (acum.compareTo(n) != 1)
      acum = acum.multiply(primo);
    acum = acum.divide(primo);
    return acum;
  }

  /**
   * Halla un factor de n.
   * La probabilidad de exito del algoritmo es 0.5, luego intentandolo t veces
   * tendremos una probabilidad de fracaso de 0.5^t.
   * @param n Numero a factorizar
   * @param B Los factores se suponen menores que B (maximo: B = 1000)
   * @param t Intentos del algoritmo
   * @return Factor hallado. Si n es primo devuelve 1
   */
  public static BigInteger devuelveFactor(BigInteger n,int B,int t)
  {
    BigInteger q = new BigInteger("1");
    int i = 0;
    BigInteger primo = new BigInteger("0");
    double c = 0;
    // Hallamos el numero al que elevaremos a
    // q = productorio (p^parteEntera(log(n))) | p es primo y menor que B
    while ((i<primosB.length) && (primosB[i]<B))
    {
      primo = new BigInteger(String.valueOf(primosB[i]));
      // primo elevado devuelve p^parteEntera(log(n))
      q = q.multiply(primoElevado(primo,n));
      i++;
    }

    BigInteger a = new BigInteger("0");
    BigInteger x = new BigInteger("0");
    BigInteger d = new BigInteger("1");    // factor extraido
    i=0; // cuenta las vueltas de seguridad

    // while i<t && (d<=1 || d>=n)
    while ( (i<t) &&
           ((d.compareTo(BigInteger.ONE)!=1) //d<=1
            ||
            (d.compareTo(n)!= -1 )           //d>=n
           )
          )
    {
      BigDecimal aleatorio = new BigDecimal(Math.random());
      aleatorio = aleatorio.multiply(new BigDecimal(n.subtract(new BigInteger("1"))));
      aleatorio = aleatorio.add(new BigDecimal(2.0));
      a = aleatorio.toBigInteger();    //a = aleatorio entre 2..n-1
      d = a.gcd(n);                    //si mcd(n,a) es mayor que uno hemos acabao
      if (d.compareTo(BigInteger.ONE)== 0) // menor o igual que uno
      {
        x = a.modPow(q,n);                  //x = a^q mod n
        x = x.subtract(BigInteger.ONE);     //x = x-1
        d = x.gcd(n);                       //d = mcd(x-1,n)
      }
      i++;
    }
    // si no hemos conseguido factorizar (porque es primo) devolvemos 1
    if ((d.compareTo(BigInteger.ONE)!=1) //d<=1
        ||
        (d.compareTo(n)!= -1 ))          //d>=n
       d = BigInteger.ONE;

    return d;
  }

  /**
   * Factoriza un numero, devolviendo sus factores primos.
   * Al usar la clase BigInteger, el numero puede ser de cualquier longitud.
   * @param n Numero a factorizar
   * @param B Los factores se suponen menores que B (maximo: B = 1000)
   * @param t Intentos del algoritmo
   * @return Cadena de caracteres con la factorizacion
   */
  public static String factoriza(BigInteger n, int B, int t)
  {
    BigInteger factor = Codify.devuelveFactor(n,B,t);
    if (factor.compareTo(BigInteger.ONE) == 0)
      return n.toString();
    else if (n.compareTo(factor) == 0)
      return Codify.factoriza(factor,B,t);
    else
      return Codify.factoriza(factor,B,t) +" "+
             Codify.factoriza(n.divide(factor),B,t);
  }

/*       Cifrador de Vernam         */


  /**
   * Semilla del SRL
   */
  private static int[] semilla = null;

  /**
   * Establece la semilla del SRL
   * @param sem
   */
  public static void semilla (int[] sem)
  {
    semilla = new int[sem.length];
    for (int i = 0; i< sem.length ; i++)
      semilla[i]  =  sem[i] % 2;
  }

  /**
   * Secuencia del SRL
   */
  private static int[] pol = null;

  /**
   * Establece la secuencia del SRL
   * @param poli
   */
  public static void polinomio (int[] poli)
  {
    pol = new int[poli.length];
    for (int i = 0; i<poli.length; i++)
      pol[i]  =  poli[i] % 2;
  }

  /**
   * Genera un bit de la SRL
   * @return
   */
  public static int generaBit()
  {
      int acum = 0;
      for (int i = 0; i< pol.length; i++)
      {
        acum += semilla[i] * pol[i];
        if (i< pol.length-1) semilla[i]=semilla[i+1];
      }
      acum = acum % 2;
      semilla[pol.length -1] = acum;
      return acum;
  }

  /**
   * Transforma un numero en binario a entero
   * El num binario se representa como un array de 1 y 0
   * @param cad
   * @return
   */
  public static int NumeroAleatorio(int[] cad)
  {
    int num = 0;
    int potencia = 1;   //2^0
    int bit = 0;
    for (int i = 0;  i<cad.length; i++ )
    {
      bit  = cad[i];
      num += (bit * potencia);
      potencia *= 2;
    }
    return num;
  }

  /**
   * Alfabeto del cifrador
   */
  private static String alfabeto = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ 123456789";

  /**
   * Devueve el ordinal de un caracter
   * @param car
   * @return
   * @throws Exception
   */
  public static int ord(char car) throws Exception
  {
    int i=0;
    while ((i<alfabeto.length())&&(alfabeto.charAt(i)!=car)) i++;
    if (i==alfabeto.length())
        throw new Exception("El caracter: "+car+" no se encuentra en el alfabeto");
    return i;
  }

  /**
   * Devuelve el caracter con dicho ordinal
   */
  public static char chr(int num) throws Exception
  {
    if (num >= alfabeto.length())
        throw new Exception("El caracter: "+num+" no existe");
    return alfabeto.charAt(num);
  }


  /**
   * Cifrador/Descifrador de Vernam
   * Para cifrar se introduce el texto en claro
   * Para descifrar se introduce el texto cifrado
   * Utiliza un alfabeto de 6 bits.
   * @param limpio
   * @return
   * @throws Exception
   */
  public static String[] cifradorVernam(String limpio) throws Exception
  {
    String codificado = "";
    String srl = "";

    int[] aleatorio = new int[6];  //Alfabeto de 6 bits
    int lim = 0;
    int su = 0;
    int bit = 0;

    // recorro el texto
    for (int i=0; i<limpio.length(); i++)
    {
      lim = Codify.ord(limpio.charAt(i));
      // genero un num. de 6 bits segun la SRL
      for (int j = 0; j<6; j++)
      {
        bit = Codify.generaBit();
        aleatorio[j] = bit;
        srl += bit;
      }
      // obtengo el valor entero del numero en binario
      su =  Codify.NumeroAleatorio(aleatorio);
      // hago la XOR del caracter en claro con el caracter obtenido de la SRL
      codificado+= Codify.chr(su^lim);
    }
      String [] ret =  new String[2];
      ret[0] = codificado;
      ret[1] = srl;
      return ret;
  }


  public static void main(String[] args)
  {
    int p[] = {0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1};
    int s[] = {1,1,0,0,1,0,1,1,1,0,0,0,1,1,0,0};
    Codify.polinomio(p);
    Codify.semilla(s);
  }
}

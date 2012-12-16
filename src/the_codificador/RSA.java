/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package the_codificador;

/**
 *
 * @author roberacc
 */
import java.math.BigInteger;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Algunas personas
 */
public class RSA {

    static int tamPrimo;
    BigInteger z, q, p;
    BigInteger f;
    BigInteger n, d;
     String msj="";

    /** Constructor de la clase RSA */
    public RSA(int tamPrimo) {
    
        this.tamPrimo = tamPrimo;
        generaPrimos();             //Genera p y q
        generaClaves();             //Genera n y d
    }
    
    public RSA(){
        
    }
    
    public void generaPrimos()
    {
        p = new BigInteger(String.valueOf(gen_Primo()));
      
        q = new BigInteger(String.valueOf(gen_Primo()));
     
    }
    
    
    public void generaClaves()
    {
        // n = p * q
        z = p.multiply(q);
       
        // f = (p-1)*(q-1)
        f = p.subtract(BigInteger.valueOf(1));
        f = f.multiply(q.subtract(BigInteger.valueOf(1)));
       
        // Elegimos un n coprimo de y menor que n
        do n = new BigInteger(String.valueOf(gen_Primo()));
            while((n.compareTo(f) != -1) ||
		 (n.gcd(f).compareTo(BigInteger.valueOf(1)) != 0));
        // d = n^1 mod f
        d = n.modInverse(f);
    }
    
    public static long gen_Primo() {
        boolean found = false;
        long primoA = 0;
        int c = 1;

        primoA = (long) (Math.random() * 9000000 + 1000000);

        while (!found) {

            found = miller_rabin(primoA + c);
            if (found) {
                primoA = primoA + c;
            }

            c++;
        }
        return primoA;
    }
    
    public static boolean miller_rabin(long n) {
        if (n <= 2) {
            return false;
        } else if (miller_rabin_pass(2, n)
                && (miller_rabin_pass(7, n))
                && (miller_rabin_pass(61, n))) {
            return true;
        } else {
            return false;
        }
    }
    
    private static boolean miller_rabin_pass(long a, long n) {
        int d = (int) (n - 1);
        System.out.println("d = " + d);
        long valorModulo = modular_exponent(a, d, n);
        if (valorModulo == 1) {
            return true;
        } 
        for (int i = 0; i < a; i++) {
            if (valorModulo == n - 1) {
                return true;
            }
            valorModulo = modular_exponent(valorModulo, 2, n);
        }
        if (valorModulo == n - 1) {
            return true;
        }
        return false;
    }
    
    private static int modular_exponent(long base, long power, long modulus) {

        long result = 1;
        for (int i = 31; i >= 0; i--) {
            result = (result * result) % modulus;
            if ((power & (1 << i)) != 0) {
                result = (result * base) % modulus;
            }
        }
        return (int) result; 
    }
    
    
    /**
     * Encripta el texto usando la clave pública
     *
     * @param   mensaje     Ristra que contiene el mensaje a encriptar
     * @return   El mensaje cifrado como un vector de BigIntegers
     */
    public BigInteger[] encripta(String mensaje)
    {
         
        int i;
        byte[] temp = new byte[1];
        byte[] digitos = mensaje.getBytes();
        BigInteger[] bigdigitos = new BigInteger[digitos.length];
       
        for(i=0; i<bigdigitos.length;i++){
            temp[0] = digitos[i];
            bigdigitos[i] = new BigInteger(temp);
        }
        
        BigInteger[] encriptado = new BigInteger[bigdigitos.length];
        
        for(i=0; i<bigdigitos.length; i++)
            encriptado[i] = bigdigitos[i].modPow(n,z);
        return(encriptado);
    }
  
    public String desencripta(BigInteger[] encriptado) {
        BigInteger[] desencriptado = new BigInteger[encriptado.length];
        
        for(int i=0; i<desencriptado.length; i++)
            desencriptado[i] = encriptado[i].modPow(d,z);
        
        char[] charArray = new char[desencriptado.length];
        
        for(int i=0; i<charArray.length; i++)
            charArray[i] = (char) (desencriptado[i].intValue());
        
        return(new String(charArray));
    }
    
    public BigInteger getP() {return(p);}
    public BigInteger getQ() {return(q);}
    public BigInteger getF() {return(f);}
    public BigInteger getN() {return(z);}
    public BigInteger getE() {return(n);}
    public BigInteger getD() {return(d);}
    
    
    
}



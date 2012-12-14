/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package the_codificador;

/**
 *
 * @author Owner
 */
import java.math.BigInteger;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Algunas personas
 */
public class RS {

    static int tamPrimo;
    BigInteger z, q, p;
    BigInteger f;
    BigInteger n, d;
     String msj="";

    /** Constructor de la clase RSA */
    public RS(int tamPrimo) {
       
        this.tamPrimo = tamPrimo;
        generaPrimos();             //Genera p y q
        generaClaves();             //Genera n y d
    }
    
    public void generaPrimos()
    {
        p = new BigInteger(String.valueOf(gen_Primo()));
        //System.out.println("valor p: "+p);
        q = new BigInteger(String.valueOf(gen_Primo()));
        System.out.println("valor q: "+q);
            
//        p = new BigInteger(tamPrimo, 10, new Random());
//        System.out.println("valor p: "+p);
//        do q = new BigInteger(tamPrimo, 10, new Random());
//            while(q.compareTo(p)==0);
    }
    
    
    public void generaClaves()
    {
        // n = p * q
        z = p.multiply(q);
       
        // toltient = (p-1)*(q-1)
        f = p.subtract(BigInteger.valueOf(1));
        f = f.multiply(q.subtract(BigInteger.valueOf(1)));
       
        // Elegimos un n coprimo de y menor que n
        do n = new BigInteger(String.valueOf(gen_Primo()));
            while((n.compareTo(f) != -1) ||
		 (n.gcd(f).compareTo(BigInteger.valueOf(1)) != 0));
        // d = n^1 mod totient
       
        d = n.modInverse(f);
    }
    
    public static long gen_Primo() {
        boolean found = false;
        long primoA = 0;
        int c = 1;

        primoA = (long) (Math.random() * 9000000 + 1000000);

        if(primoA % 2 == 0)
            primoA = (long) (Math.random() * 9000000 + 1000000);
        
        while (!found) {
            //System.out.println("verificar: " + (primoA + c));

            found = miller_rabin(primoA + c);
            //System.out.println(found);
            if (found) {
                primoA = primoA + c;
            }

            c++;
        }
        return primoA;
    }
    
    public static boolean miller_rabin(long n) {
        
        
        
        
//        if (n <= 2) {
//            return false;
//        } else if (miller_rabin_pass(2, n)
//                && (n <= 7 || miller_rabin_pass(7, n))
//                && (n <= 61 || miller_rabin_pass(61, n))) {
//            return true;
//        } else {
          return false;
//        }
    }
    
    private static boolean miller_rabin_pass(long a, long n) {
        int d = (int) (n - 1);
        int s = Integer.numberOfTrailingZeros(d);
       
        d >>= s;
        
        long valorModulo = modular_exponent(a, d, n);
        if (valorModulo == 1) {
             System.out.println("1");
            return true;
        } 
        for (int i = 0; i < s - 1; i++) {
            if (valorModulo == n - 1) {
                System.out.println("a.n-1");
                return true;
            }
            valorModulo = modular_exponent(valorModulo, 2, n);
        }
        if (valorModulo == n - 1) {
            System.out.println("b.n-1");
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
            //System.out.println("size: "+bigdigitos.length);
            encriptado[i] = bigdigitos[i].modPow(n,z);
        System.out.println("encrip: "+encriptado[0]);
        return(encriptado);
    }
    
    /**
     * Desencripta el texto cifrado usando la clave privada
     *
     * @param   encriptado       Array de objetos BigInteger que contiene el texto cifrado
     *                           que será desencriptado
     * @return  The decrypted plaintext
     */
    public String desencripta(BigInteger[] encriptado) {
        BigInteger[] desencriptado = new BigInteger[encriptado.length];
        
        for(int i=0; i<desencriptado.length; i++)
            desencriptado[i] = encriptado[i].modPow(d,z);
        
        char[] charArray = new char[desencriptado.length];
        
        for(int i=0; i<charArray.length; i++)
            charArray[i] = (char) (desencriptado[i].intValue());
        
        return(new String(charArray));
    }
    
    public BigInteger damep() {return(p);}
    public BigInteger dameq() {return(q);}
    public BigInteger dametotient() {return(f);}
    public BigInteger damen() {return(z);}
    public BigInteger damee() {return(n);}
    public BigInteger damed() {return(d);}
    
    
    
}
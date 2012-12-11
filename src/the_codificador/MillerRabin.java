/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package the_codificador;

import javax.swing.JOptionPane;

/**
 *
 * @author roberacc
 */
public class MillerRabin {

    
    static long primo1 = 0;
    static long primo2 = 0;

    private static int modular_exponent(long base, long power, long modulus) {
        long result = 1;
        for (int i = 31; i >= 0; i--) {
            result = (result * result) % modulus;
            if ((power & (1 << i)) != 0) {
                result = (result * base) % modulus;
            }
        }
        return (int) result; // Will not truncate since modulus is an int
    }

    private static boolean miller_rabin_pass(long a, long n) {
        int d = (int) (n - 1);
        int s = Integer.numberOfTrailingZeros(d);
        d >>= s;
        long valorModulo = modular_exponent(a, d, n);
        if (valorModulo == 1) {
            return true;
        }
        for (int i = 0; i < s - 1; i++) {
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

    public static boolean miller_rabin(long n) {
        if (n <= 2) {
            return false;
        }else if (miller_rabin_pass(2, n)
                && (n <= 7 || miller_rabin_pass(7, n))
                && (n <= 61 || miller_rabin_pass(61, n))) {
            return true;
        } else {
            return false;
        }
    }
    
    public static long gen_Primo(){
        boolean found = false;
        long primoA = 0;
        int c = 1;
        
        primoA = (long) (Math.random() * 9000000 + 1000000);
        
        while (!found) {
            System.out.println("verificar: " + (primoA + c));

            found = miller_rabin(primoA + c);
            //System.out.println(found);
            if (found) {
                primoA = primoA+c;
                System.out.println(primoA );
            }

            c++;
        }
        return primoA;
    }

    public static void main(String[] args) {
        long n = Long.parseLong(JOptionPane.showInputDialog(null, "Numero"));
        
        System.out.println(miller_rabin(n) ? n + " Es Primo" : n + " Es Compuesto");
        primo1 = gen_Primo();
        primo2 = gen_Primo();
        
        JOptionPane.showMessageDialog(null, "Primo 1: "+primo1+"\nPrimo 2: "+primo2);

    }
}
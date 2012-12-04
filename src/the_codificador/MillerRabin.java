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
public class MillerRabin{
    
     private static int modular_exponent(long base, long power, long modulus) {
         long result = 1;
         for (int i = 31; i >= 0; i--) {
             result = (result*result) % modulus;
             if ((power & (1 << i)) != 0) {
                 result = (result*base) % modulus;
             }
         }
         return (int)result; // Will not truncate since modulus is an int
     }
 
 
     private static boolean miller_rabin_pass(long a, long n) {
         int d = (int) (n - 1);
 	int s = Integer.numberOfTrailingZeros(d);
 	d >>= s;
         long a_to_power = modular_exponent(a, d, n);
         if (a_to_power == 1) return true;
         for (int i = 0; i < s-1; i++) {
             if (a_to_power == n-1) return true;
             a_to_power = modular_exponent(a_to_power, 2, n);
         }
         if (a_to_power == n-1) return true;
         return false;
     }
 
     public static boolean miller_rabin(long n) {
         if (n <= 1) return false;
         else if (n == 2) return true;
         else if (miller_rabin_pass( 2, n) &&
             (n <= 7  || miller_rabin_pass( 7, n)) &&
             (n <= 61 || miller_rabin_pass(61, n)))
             return true;
         else
             return false;
     }
 
     public static void main(String[] args) {
         long n = Long.parseLong(JOptionPane.showInputDialog(null,"Numero"));
         long primo=0;
         boolean found=false;
         int c=1;
         
         System.out.println(miller_rabin(n) ? n+" Es Primo" : n+" Es Compuesto");
         
         if(miller_rabin(n)){
             found=true;
         }
         
         primo=(long) (Math.random()*9000000+1000000);
         
         System.out.println();
         
            while(!found){
                System.out.println("verificar: "+(primo+c));
                System.out.println("hiii");
                
                found = miller_rabin(primo+c);
                System.out.println(found);
                if(found){
                    System.out.println(primo+c);
                }
                
                c++;
            }
         
//         System.out.println(miller_rabin(primo));1243631
//         System.out.println(miller_rabin(primo) ? "Primo" : miller_rabin(primo+1));
     }
 }
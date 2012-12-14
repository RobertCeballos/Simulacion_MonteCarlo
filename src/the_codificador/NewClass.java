/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package the_codificador;

/**
 *
 * @author Owner
 */
public class NewClass {
    
    
    public static void main(String []args){
    
        int val = modular_exponent(6, 3, 7);
        System.out.println("val = " + val);
        
    }
            
        private static int modular_exponent(long base, long power, long modulus) {

        boolean found=false;    
        long result = 1;
        while(!found) {
            
            if (power % 2 == 1) {
                result = (result * base) % modulus;
                found = true;
            }
            base = (long) (Math.pow(base, 2) % modulus);
            power = power / 2;
            System.out.println("power = " + power);
        }
        return (int) result; 
    }
}

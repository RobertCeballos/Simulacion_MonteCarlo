/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package the_codificador;

/**
 *
 * @author roberacc
 */
public class NewClass {
    
   private static int modular_exponent_32(int base, int power, int modulus) {
    long result = 1;
    for (int i = 31; i >= 0; i--) {
        result = (result*result) % modulus;
        if ((power & (1 << i)) != 0) {
            result = (result*base) % modulus;
        }
    }
    return (int)result; // Will not truncate since modulus is an int
}
    
   
   public static double expRapida(double num,double b,double n){
    double z, x, resul;
    z = b;
    x = num;
    resul = 1;

    while (z>0)
    {
      if ((z % 2)== 1)
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
    
    
    public static void main(String[] arg){
        int valor=0;
        
       //valor = modular_exponent_32(5,21,11);
       
       valor = (int) expRapida(5,21,11);
       
       System.out.println(valor);
    }
    
}

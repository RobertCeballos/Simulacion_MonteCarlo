/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package the_codificador;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author roberacc
 */
public class NewClass {
    
    String msj="";
    char dato='a';
    static int datN=0;
    static  long n, q, p;
    static long z=4163171;
    static int tamPrimo=1244;
    
    public NewClass(){
        //generaPrimos();
        co_Primos(23,4868833);
        
        
    }
    
    public static boolean co_Primos(long a, long b) {
        boolean found = true;

        for (int i = 0; i < b; i++) {
            long t = (long) (a * i);

            if (t > b) {
                found = true;
                break;
            } else if (t == b) {
                found = false;
                break;
            }

            return found;
        }


        return found;
    }
    
//    public void generaPrimos()
//    {
//        p = 3;
//        System.out.println("valor p: "+p);
//        do q = 13;
//            while(q.compareTo(p)==0);
//    }
    
    public static void main(String []args){
        
       datN =(int) 'A';
       System.out.println("codigo ASCII de la letra a: "+datN);
       int p = (int) (Math.random()*20 +1);
       n = (long) (Math.random() * (z - 2) + 1);
       System.out.println("nnnnnnnn"+n+"ppppp"+p);
       //NewClass obj = new NewClass();
       
       
       
        
    }
}

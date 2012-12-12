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
    BigInteger n, q, p;
    static int tamPrimo=1244;
    
    public NewClass(){
        generaPrimos();
        
        
    }
    
    public void generaPrimos()
    {
        p = new BigInteger(tamPrimo, 10, new Random());
        System.out.println("valor p: "+p);
        do q = new BigInteger(tamPrimo, 10, new Random());
            while(q.compareTo(p)==0);
    }
    
    public static void main(String []args){
        
       datN =(int) 'A';
       System.out.println("codigo ASCII de la letra a: "+datN);
       NewClass obj = new NewClass();
       
       
       
        
    }
}

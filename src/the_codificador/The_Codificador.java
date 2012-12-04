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
public class The_Codificador {
    
    int arreglo[];
    String msj="";
    boolean out;
    
    
    public The_Codificador(int cantidad, int limite){
        String salida="";
        arreglo = new int[cantidad];
        //generadorUniforme();
         out = primoFermat(cantidad, limite);
         
          if(out==true){
            salida="true";
        }else{
            salida="false";
        }
         //System.out.println(salida);
         millerRobin(23);
        
    }
    
    public void genUniforme(){
        int dato=0;
        
        for (int i = 0; i < arreglo.length; i++) {
           dato=(int) (Math.round(Math.random()*(arreglo.length - 1))+1);
           
           System.out.println(dato);
            
        }
    }
    
    
    public static void millerRobin(int n){
        boolean found = false;
        int valor=0;
        int s=1;
        int t=0;
        
        while(!found){
            
            valor = ((int) Math.pow(2, s))* t + 1;
            if(valor == n){
                found=true;
                System.out.println("ttt: "+t);
                break;
            }
            t++;
            System.out.println("tt: "+t);
        }
        System.out.println("t: "+t);
    }
    
    
    public static boolean primoFermat(int n, int t){
    int a,b;
    boolean primo = false;
    int i = 0;
    // bucle principal.
    while(!primo || (i<t))
    {
      a =(int) Math.floor(2+(Math.random()* (n-2))); // a = aleatorio 2..n-1
      System.out.println(a);
      b =(int) expRapida(a,n-1,n);        // b = a^n-1 mod n
      i++;
      //primo = false;
      
      primo = (b==1);       // si b = 1 entonces es primo.
      System.out.println(primo+" ."+b+" i:"+i);
    }
    return primo;
  }

    
    
    public static boolean esPrimo(int dato){
        boolean isPrime=false;
        String salida="";
        
        if(dato==2||dato==3)
            return true;
        
        if(dato % 2 == 0)
            return false;
        
        if(dato < 2)
            return false;
        
        for (int i = 3; i <=(dato/2); i+=2) {
            if(dato % i == 0)
                 return false;
        }
        
       
        return true;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int num = Integer.parseInt(JOptionPane.showInputDialog("numero a probar"));
        int lim = Integer.parseInt(JOptionPane.showInputDialog("limite de pruebas"));
        The_Codificador obj = new The_Codificador(num, lim);
        // TODO code application logic here
    }
}

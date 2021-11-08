
package Model.EXE;

import Model.Metodos.Automato;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Pedro Citadin Coelho
 */
public class Main {
       public static void main(String[] args) {
       Scanner ler = new Scanner(System.in);
       ArrayList<String> lista=  new ArrayList<String>();    
       Automato at = new Automato();
           System.out.println("Digite os termos não terminais (variaveis), separados por virgula (Ex: S,A,B...): ");
           String nt = ler.nextLine();
           at.defineNTerminais(nt);
           
           
           System.out.println("Digite os termos terminais, separados por virgula (Ex: a, b...): : ");
           String t = ler.nextLine();
           at.defineTerminais(t);
           
           System.out.println("Digite as regras separadas por enter e /(Ex: S/aSb) quando encerrar pressione 0: ");
           String r = null;
           do{
               r = ler.nextLine();
               if (!r.equalsIgnoreCase("0")) {
                   lista.add(r);
               }
           }while(!r.equalsIgnoreCase("0"));
           at.defineRegras(lista);
           System.out.println("Digite o termo de inicio (Ex: S): ");
           String i  = ler.nextLine();
           at.setInicio(i);
           System.out.println(at.computa());
           System.out.println("------------------");
           System.out.println(at.computa());
           
           
            


          
       
    }
}


package Model.Metodos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Pedro Citadin Coelho
 */
public class Automato {
    private ArrayList<String> nTerminais;
    private ArrayList<String> terminais;
    private ArrayList<Regras> regras;
    private String inicio;
    private Stack<String> producao;

    public void defineNTerminais(String a){
        nTerminais = new ArrayList<String>();
        String [] aux = a.split(",");
        
        for (int i = 0; i < aux.length; i++) {
            nTerminais.add(aux[i].replaceAll("\\s", ""));
        }
        
    }
    public void defineTerminais(String a){
        terminais = new ArrayList<String>();
        String [] aux = a.split(",");
        for (int i = 0; i < aux.length; i++) {
            terminais.add(aux[i].replaceAll("\\s", ""));
        }
        
    }
    public void defineRegras(ArrayList<String> a ){
        regras = new ArrayList<Regras>();
        for (String aux:a) {
            String[] aux2 = aux.split("/");
            Regras r = new Regras(aux2[0], aux2[1]);
            regras.add(r);
        }
    }
    public void setInicio(String e){
        this.inicio = e;
    }
    public void escreveNTerminais(){
        for (String nt: nTerminais) {
            System.out.println(nt);
        }
    }
    public void escreveTerminais(){
        for (String t: terminais) {
            System.out.println(t);
        }
    }
    public void escreveRegras(){
        for (Regras r: regras) {
            System.out.println(r.toString());
        }
    }
    
    public String computa(){
        producao = new Stack<String>();
        String saida = "";
        
        if (verificaTerminal(inicio)) {
            
            return inicio;
        }
        
        ArrayList<Regras> aux = new ArrayList<Regras>();
        for (Regras r: regras) {
            if (r.getSimbolo().equals(inicio)) {
                aux.add(r);
                
            }
        }
        Random ale = new Random();
        
        String regra = aux.get(ale.nextInt(aux.size())).getResultado();
        System.out.println(regra);
        for (int i = regra.length()-1; i >= 0; i--) {
            
            producao.add(String.valueOf(regra.charAt(i)));
        }
       
        while(!producao.isEmpty()){
            
            if (verificaTerminal(producao.peek())) {
               
                
                saida = saida +producao.peek();
                producao.pop();
            }else{
                inserePilha(producao.peek());
                
            }
            
        }
        
        
        return saida;
    }
    
    public boolean verificaTerminal(String l){
        for (String aux: terminais) {
            if (aux.equals(l)) {
                
                return true;
                
            }
        }
        
        return false;
    }
    public void inserePilha(String s){
        producao.pop();
        ArrayList<Regras> aux = new ArrayList<Regras>();
        for (Regras r: regras) {
            if (r.getSimbolo().equals(s)) {
                aux.add(r);
            }
        }
        Random ale = new Random();
        String regra = aux.get(ale.nextInt(aux.size())).getResultado();
       
        for (int i = regra.length()-1; i >= 0; i--) {
            producao.push(String.valueOf(regra.charAt(i)));
        }
    }
    
}

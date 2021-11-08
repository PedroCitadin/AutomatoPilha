
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
    
    
    ////Pega os termos não terminais
    public void defineNTerminais(String a){
        nTerminais = new ArrayList<String>();
        String [] aux = a.split(",");
        
        for (int i = 0; i < aux.length; i++) {
            nTerminais.add(aux[i].replaceAll("\\s", ""));
        }
        
    }
    
    ////Pega os termos terminais
    public void defineTerminais(String a){
        terminais = new ArrayList<String>();
        String [] aux = a.split(",");
        for (int i = 0; i < aux.length; i++) {
            terminais.add(aux[i].replaceAll("\\s", ""));
        }
        
    }
    
    ////Pega a lista de regras
    public void defineRegras(ArrayList<String> a ){
        regras = new ArrayList<Regras>();
        for (String aux:a) {
            String[] aux2 = aux.split("/");
            Regras r = new Regras(aux2[0], aux2[1]);
            regras.add(r);
        }
    }
    
    //pega o termo de inicio
    public void setInicio(String e){
        this.inicio = e;
    }
    
    ///Esgreve os termos não terminais
    public void escreveNTerminais(){
        for (String nt: nTerminais) {
            System.out.println(nt);
        }
    }
    
    ///Esgreve os termos terminais
    public void escreveTerminais(){
        for (String t: terminais) {
            System.out.println(t);
        }
    }
    
    ///Esgreve as regras
    public void escreveRegras(){
        for (Regras r: regras) {
            System.out.println(r.toString());
        }
    }
    
    
    ////Faz o processamento
    public String computa(){
        producao = new Stack<String>();
        String saida = "";
        
        ///verifica se o termo de inicio não é terminal
        if (verificaTerminal(inicio)) {
            
            return inicio;
        }
        
        ///consulta a regra referente ao termo de inicio
        ArrayList<Regras> aux = new ArrayList<Regras>();
        for (Regras r: regras) {
            if (r.getSimbolo().equals(inicio)) {
                aux.add(r);
                
            }
        }
        
        ///faz uma escolha aleatória dentre as opções de regras
        Random ale = new Random();
        
        String regra = aux.get(ale.nextInt(aux.size())).getResultado();
        System.out.println(regra);
        
        ///adiciona o resultado da regra na lista de produçao
        for (int i = regra.length()-1; i >= 0; i--) {
            
            producao.add(String.valueOf(regra.charAt(i)));
        }
       
        
        ///enquanto a lista de produção não estiver vazia os termos são processados
        while(!producao.isEmpty()){
            
            ///verifica se o termo é terminal
            if (verificaTerminal(producao.peek())) {
               
                ///caso terminal, o termo é inserido na string de saída e removido da lista de produção
                saida = saida +producao.peek();
                producao.pop();
            }else{
                
                ////caso não terminal o termo é inserido na lista de produção e reprocessado
                inserePilha(producao.peek());
                
            }
            
        }
        
        ///retorna uma palavra valida aleatória
        return saida;
    }
    
    
    ///metodo para verifica se um termo é terminal
    public boolean verificaTerminal(String l){
        for (String aux: terminais) {
            if (aux.equals(l)) {
                
                return true;
                
            }
        }
        
        return false;
    }
    
    ///metodo para inserir um termo na pilha
    public void inserePilha(String s){
        producao.pop();
        ArrayList<Regras> aux = new ArrayList<Regras>();
        
        ///consulta qual regra é referente ao termo
        for (Regras r: regras) {
            if (r.getSimbolo().equals(s)) {
                aux.add(r);
            }
        }
        
        ///faz uma escolha aleatória entre as opções da regra
        Random ale = new Random();
        String regra = aux.get(ale.nextInt(aux.size())).getResultado();
       
       ///põe os termos do resultado da regra na lista de produção
        for (int i = regra.length()-1; i >= 0; i--) {
            producao.push(String.valueOf(regra.charAt(i)));
        }
    }
    
}

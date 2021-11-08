
package Model.Metodos;

/**
 *
 * @author Pedro Citadin Coelho
 */
public class Regras { ////Ex: S -> aSb
    private String simbolo; ///Ex: S
    private String resultado;/// Ex: aSb

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Regras(String simbolo, String resultado) {
        this.simbolo = simbolo;
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return simbolo+"->"+resultado;
    }
    
    
}

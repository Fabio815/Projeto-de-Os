package br.com.model;

public class Filtro {
    private String propriedade;
    private String operador;
    private String valor;

    public String getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Filtro{" +
                "propriedade='" + propriedade + '\'' +
                ", operador='" + operador + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}

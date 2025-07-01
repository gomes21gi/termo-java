package model;

public class Palavra {
    private String texto;

    public Palavra(String texto) {
        this.texto = texto.toUpperCase();
    }

    public String getTexto() {
        return texto;
    }
}
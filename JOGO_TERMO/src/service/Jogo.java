package service;

import model.Jogador;

public abstract class Jogo {
    protected Jogador jogador;

    public Jogo(Jogador jogador) {
        this.jogador = jogador;
    }

    public abstract void iniciar();
}
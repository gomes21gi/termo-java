package main;

import model.Jogador;
import service.JogoTermo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== JOGO TERMO =====");
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine();

        Jogador jogador = new Jogador(nome);
        JogoTermo jogo = new JogoTermo(jogador);

        jogo.iniciar();
    }
}
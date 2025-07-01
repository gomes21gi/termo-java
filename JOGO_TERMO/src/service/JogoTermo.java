package service;

import model.Jogador;
import model.Palavra;
import util.ArquivoUtils;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JogoTermo extends Jogo implements Verificador {
    private Palavra palavraSecreta;
    private List<Palavra> listaPalavras;

    public JogoTermo(Jogador jogador) {
        super(jogador);
        this.listaPalavras = ArquivoUtils.carregarPalavras("C:\\Users\\m76je\\OneDrive\\Documents\\Univille\\Mateus\\Semestre 3\\ARQUITETURA DE COMPUTADORES\\PROJETO-DETECTOR-MOVIMENTO\\JOGO_TERMO\\src\\data\\palavras.txt");
        this.palavraSecreta = sortearPalavra();
    }

    private Palavra sortearPalavra() {
        Random rand = new Random();
        return listaPalavras.get(rand.nextInt(listaPalavras.size()));
    }

    @Override
    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem-vindo, " + jogador.getNome() + "!");

        boolean continuarJogando = true;

        while (continuarJogando) {
            this.palavraSecreta = sortearPalavra();
            boolean acertou = false;
            int tentativas = 0;

            while (!acertou && tentativas < 6) {
                System.out.print("Digite uma palavra de 5 letras: ");
                String tentativa = sc.nextLine().toUpperCase();

                if (tentativa.length() != 5) {
                    System.out.println("A palavra deve ter 5 letras.");
                    continue;
                }

                tentativas++;
                acertou = verificar(tentativa);
            }

            if (acertou) {
                int pontosGanhos = 7 - tentativas;
                System.out.println("Parabéns, você acertou!");
                jogador.setPontos(jogador.getPontos() + pontosGanhos);
                System.out.println("Você ganhou " + pontosGanhos + " pontos.");

                System.out.print("Deseja jogar novamente? (S/N): ");
                String resposta = sc.nextLine().trim().toUpperCase();
                if (!resposta.equals("S")) {
                    continuarJogando = false;
                }
            } else {
                System.out.println("Você perdeu. A palavra era: " + palavraSecreta.getTexto());
                continuarJogando = false;
            }
        }

        ArquivoUtils.salvarRanking(jogador, "C:\\Users\\m76je\\OneDrive\\Documents\\Univille\\Mateus\\Semestre 3\\ARQUITETURA DE COMPUTADORES\\PROJETO-DETECTOR-MOVIMENTO\\JOGO_TERMO\\src\\data\\ranking.txt");
    }

    @Override
    public boolean verificar(String tentativa) {
        String palavra = palavraSecreta.getTexto();
        StringBuilder resultado = new StringBuilder();

        final String VERDE = "\u001B[32m";
        final String AMARELO = "\u001B[33m";
        final String VERMELHO = "\u001B[31m";
        final String RESET = "\u001B[0m";

        for (int i = 0; i < palavra.length(); i++) {
            char letra = tentativa.charAt(i);
            if (letra == palavra.charAt(i)) {
                resultado.append(VERDE).append(letra).append(RESET).append(" ");
            } else if (palavra.contains(String.valueOf(letra))) {
                resultado.append(AMARELO).append(letra).append(RESET).append(" ");
            } else {
                resultado.append(VERMELHO).append(letra).append(RESET).append(" ");
            }
        }

        System.out.println(resultado.toString());
        return tentativa.equals(palavra);
    }
}
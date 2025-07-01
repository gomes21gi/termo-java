package util;

import model.Jogador;
import model.Palavra;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtils {
    public static List<Palavra> carregarPalavras(String caminho) {
        List<Palavra> palavras = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                palavras.add(new Palavra(linha.trim()));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar palavras.");
        }
        return palavras;
    }

    public static List<Jogador> carregarRanking(String caminho) {
        List<Jogador> ranking = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    String nome = partes[0];
                    int pontos = Integer.parseInt(partes[1]);
                    Jogador jogador = new Jogador(nome);
                    jogador.setPontos(pontos);
                    ranking.add(jogador);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar ranking: " + e.getMessage());
        }

        return ranking;
    }

    public static void salvarRanking(Jogador jogador, String caminho) {
        List<Jogador> ranking = carregarRanking(caminho);

        boolean encontrado = false;
        for (Jogador j : ranking) {
            if (j.getNome().equalsIgnoreCase(jogador.getNome())) {
                j.setPontos(j.getPontos() + jogador.getPontos());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            ranking.add(jogador);
        }

        // Ordenar por pontuação decrescente
        ranking.sort((a, b) -> Integer.compare(b.getPontos(), a.getPontos()));

        try (PrintWriter writer = new PrintWriter(new FileWriter(caminho))) {
            for (Jogador j : ranking) {
                writer.println(j.getNome() + ";" + j.getPontos());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar ranking: " + e.getMessage());
        }

        // Mostrar o ranking atualizado no console
        System.out.println("\n===== RANKING =====");
        for (int i = 0; i < ranking.size(); i++) {
            Jogador j = ranking.get(i);
            System.out.println((i + 1) + ". " + j.getNome() + " - " + j.getPontos() + " pontos");
        }
    }
}
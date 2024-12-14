package com.ifpi.store.services;

import com.ifpi.store.model.Jogador;
import com.ifpi.store.model.Pontuacao;
import com.ifpi.store.repository.JogadorRepository;
import com.ifpi.store.repository.PontuacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    @Autowired
    private PontuacaoRepository pontuacaoRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    // Método para calcular a pontuação de acordo com a rodada e o número da pergunta
    public Integer calcularPontuacao(Integer rodada, Integer perguntaIndex) {
        int pontuacao = 0;

        if (perguntaIndex >= 0 && perguntaIndex < 5) { // Rodada 1
            pontuacao = 1000;
        } else if (perguntaIndex >= 5 && perguntaIndex < 10) { // Rodada 2
            pontuacao = 10000;
        } else if (perguntaIndex >= 10 && perguntaIndex < 15) { // Rodada 3
            pontuacao = 100000;
        } else if (perguntaIndex == 15) { // Rodada 4
            pontuacao = 1000000;
        }

        return pontuacao;
    }

    // Método para salvar a pontuação do jogador
    public void salvarPontuacao(Jogador jogador, Integer rodada, Integer perguntaIndex, boolean acertou) {
        if (!acertou) {
            return; // Se o jogador não acertou, não há pontuação para salvar
        }

        // Calcula a pontuação da rodada com base na pergunta
        int pontuacao = calcularPontuacao(rodada, perguntaIndex);

        Pontuacao pontuacaoRegistro = new Pontuacao();
        pontuacaoRegistro.setJogador(jogador);
        pontuacaoRegistro.setPontuacao(pontuacao);
        pontuacaoRegistro.setRodada(rodada);

        pontuacaoRepository.save(pontuacaoRegistro);
    }

    // Método para obter o ranking das 10 maiores pontuações
    public List<Pontuacao> obterRanking() {
        return pontuacaoRepository.findTop10ByOrderByPontuacaoDesc();
    }
}


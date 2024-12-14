package com.ifpi.store.controller;

import com.ifpi.store.model.Jogador;
import com.ifpi.store.model.Pergunta;
import com.ifpi.store.model.Pontuacao;
import com.ifpi.store.repository.JogadorRepository;
import com.ifpi.store.services.JogoService;
import com.ifpi.store.services.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class jogoControler {

    @Autowired
    private JogoService jogoService;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private PerguntaService perguntaService;

    @GetMapping("/iniciar-jogo")
    public String iniciarJogo(Model model) {
        // Criar um jogador fictício para exemplo (no caso, você pode criar o jogador de acordo com a interação anterior)
        Jogador jogador = new Jogador();
        jogador.setNickname("Jogador Exemplo");
        jogadorRepository.save(jogador);

        model.addAttribute("jogador", jogador);

        // Carregar perguntas e adicionar ao modelo
        List<Pergunta> perguntas = perguntaService.carregarPerguntas(); // Certifique-se de ter o método carregarPerguntas no seu PerguntaService
        model.addAttribute("perguntas", perguntas);

        return "game"; // Página do jogo
    }

    @PostMapping("/resposta")
    public String verificarResposta(Model model, Integer index, Integer rodada, String resposta, int jogadorId) {
        // Lógica para verificar a resposta
        Jogador jogador = jogadorRepository.findById(jogadorId).orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        // Carregar perguntas (por exemplo, o índice é baseado na sequência das perguntas)
        List<Pergunta> perguntas = perguntaService.carregarPerguntas();
        Pergunta perguntaAtual = perguntas.get(index);

        boolean acertou = perguntaAtual.getRespostaCorreta().equals(resposta);

        // Salva a pontuação caso tenha acertado
        jogoService.salvarPontuacao(jogador, rodada, index, acertou);

        // Redirecionar para a próxima pergunta ou finalizar
        if (index < perguntas.size() - 1) {
            return "redirect:/pergunta?index=" + (index + 1) + "&rodada=" + rodada;
        } else {
            return "redirect:/ranking"; // Após a última pergunta, exibe o ranking
        }
    }

    @GetMapping("/ranking")
    public String exibirRanking(Model model) {
        List<Pontuacao> ranking = jogoService.obterRanking();
        model.addAttribute("ranking", ranking);
        return "ranking"; // Página de ranking
    }
}

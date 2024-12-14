package com.ifpi.store.service;

import com.ifpi.store.model.Jogador;
import com.ifpi.store.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {
    private final JogadorRepository jogadorRepository;

    @Autowired
    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    public void cadastrarJogador(Jogador jogador) {
        jogadorRepository.save(jogador);
    }

    public List<Jogador> findAll() {
        return jogadorRepository.findAll();
    }
}

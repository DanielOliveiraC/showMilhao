package com.ifpi.store.repository;

import com.ifpi.store.model.Pontuacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PontuacaoRepository extends JpaRepository<Pontuacao, Long> {
    List<Pontuacao> findTop10ByOrderByPontuacaoDesc();
}


package com.ifpi.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifpi.store.model.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
}

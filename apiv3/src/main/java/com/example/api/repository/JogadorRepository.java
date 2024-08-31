package com.example.api.repository;

import com.example.api.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    boolean existsByEmail(String email);
}

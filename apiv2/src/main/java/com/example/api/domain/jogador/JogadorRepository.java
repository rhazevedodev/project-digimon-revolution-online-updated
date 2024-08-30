package com.example.api.domain.jogador;

public interface JogadorRepository {
    Jogador save(Jogador jogador);
    boolean existsByEmail(String email);
}

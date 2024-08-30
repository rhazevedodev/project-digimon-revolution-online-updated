package com.example.api.infra.jogador;

import com.example.api.domain.jogador.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJogadorRepository extends JpaRepository<Jogador, Long> {
    // Você pode definir métodos de consulta personalizados aqui, se necessário.
}

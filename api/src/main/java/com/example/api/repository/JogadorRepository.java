package com.example.api.repository;

import com.example.api.model.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    boolean existsByUsuario(String usuario);
    boolean existsByEmail(String email);
    Optional<Jogador> findById(Long idJogador);
    Optional<Jogador> findByUsuario(String usuario);

}
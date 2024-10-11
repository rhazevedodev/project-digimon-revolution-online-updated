package com.example.api.repository;

import com.example.api.entity.Conquistas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConquistasRepository extends JpaRepository<Conquistas, Long> {
    List<Conquistas> findByIdJogador(Long idJogador);

    boolean existsByIdJogador(Long idJogador);
}

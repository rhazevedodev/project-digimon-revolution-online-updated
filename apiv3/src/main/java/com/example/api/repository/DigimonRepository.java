package com.example.api.repository;

import com.example.api.entity.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DigimonRepository extends JpaRepository<Digimon, Long> {
    boolean existsByIdJogador(int idJogador);
    boolean existsByNome(String nomeDigimon);
}

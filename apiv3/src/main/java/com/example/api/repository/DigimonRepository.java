package com.example.api.repository;

import com.example.api.entity.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DigimonRepository extends JpaRepository<Digimon, Long> {
    boolean existsByIdJogador(int idJogador);
    boolean existsByNome(String nomeDigimon);

    List<Digimon> getDigimonByIdJogador(int idByUsuario);
}

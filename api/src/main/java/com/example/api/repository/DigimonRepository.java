package com.example.api.repository;

import com.example.api.model.entity.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DigimonRepository extends JpaRepository<Digimon, Long> {
    boolean existsByNomeAndIdRookie(String nome, int idRookie);

    boolean existsByNome(String nome);

    Optional<Digimon> findById(Long id);
    boolean existsByIdJogador(int idJogador);

    List<Digimon> getDigimonByIdJogador(int idJogador);
}

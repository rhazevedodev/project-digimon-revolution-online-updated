package com.example.api.repository;

import com.example.api.entity.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DigimonRepository extends JpaRepository<Digimon, Long> {
    boolean existsByIdJogador(int idJogador);
    boolean existsByNome(String nomeDigimon);
    List<Digimon> getDigimonByIdJogador(int idByUsuario);

    @Modifying
    @Transactional
    @Query("UPDATE Digimon d SET d.atributos.pontosVida = 50 * d.nivel, d.atributos.pontosEnergia = 100")
    void atualizarTodosAtributos();
}

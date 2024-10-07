package com.example.api.repository;

import com.example.api.entity.Digievolucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DigievolucaoRepository extends JpaRepository<Digievolucao, Long> {
    List<Digievolucao> findByDigimonRookie(Long id);

    @Query("SELECT d FROM Digievolucao d WHERE d.digimonChampion = :digimonChampion AND d.digimonUltimate <> 0")
    List<Digievolucao> findByDigimonChampionAndUltimateNotZero(@Param("digimonChampion") Long digimonChampion);
    @Query("SELECT d FROM Digievolucao d WHERE d.digimonUltimate = :digimonUltimate AND d.digimonMega <> 0")
    List<Digievolucao> findByDigimonUltimateAndMegaNotZero(@Param("digimonUltimate") Long digimonUltimate);
    List<Digievolucao> findByDigimonMega(Long aLong);
}

package com.example.api.repository;

import com.example.api.model.entity.TempoDisponivelCacada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TempoDisponivelCacadaRepository extends JpaRepository<TempoDisponivelCacada, Long>{
    Optional<TempoDisponivelCacada> findByIdDigimonAndDataCadastro(Long idDigimon, LocalDate dataCadastro);

    TempoDisponivelCacada findTempoDisponivelCacadaByIdDigimonAndDataCadastro(Long idDigimon, LocalDate now);
}

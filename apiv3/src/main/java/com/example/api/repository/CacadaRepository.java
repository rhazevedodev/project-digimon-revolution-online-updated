package com.example.api.repository;


import com.example.api.entity.Cacada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CacadaRepository extends JpaRepository<Cacada, Long>{
    boolean existsByIdDigimonAndRecompensaResgatadaFalse(Long idDigimon);

    Cacada findByIdDigimonAndRecompensaResgatadaFalse(Long idDigimon);
}

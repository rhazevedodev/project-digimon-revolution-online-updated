package com.example.api.repository;

import com.example.api.model.entity.Missao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissaoRepository extends JpaRepository<Missao, Long>{
    Missao findByIdDigimonAndRecompensaResgatadaFalse(Long idDigimon);
}

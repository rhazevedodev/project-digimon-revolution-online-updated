package com.example.api.repository;

import com.example.api.entity.Digievolucao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DigievolucaoRepository extends JpaRepository<Digievolucao, Long> {
    List<Digievolucao> findByDigimonRookie(Long id);
}

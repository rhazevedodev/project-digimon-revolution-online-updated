package com.example.api.repository;

import com.example.api.entity.RegistroConquistas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroConquistasRepository extends JpaRepository<RegistroConquistas, Long>{
    RegistroConquistas findByIdDigimon(Long id);
}

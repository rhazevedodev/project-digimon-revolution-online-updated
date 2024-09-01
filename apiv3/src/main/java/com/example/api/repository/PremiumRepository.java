package com.example.api.repository;


import com.example.api.entity.Premium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PremiumRepository extends JpaRepository<Premium, Long>{

    @Query("SELECT p FROM Premium p WHERE p.idDigimon = :idDigimon ORDER BY p.dataFim DESC")
    Optional<Premium> findLatestByIdDigimon(@Param("idDigimon") Long idDigimon);
}

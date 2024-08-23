package com.example.api.repository;

import com.example.api.model.entity.Invasao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvasaoRepository extends JpaRepository<Invasao, Long>{
    Invasao findFirstByDerrotado(boolean b);

    // Custom query to check if there is any record in the Invasao table
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN TRUE ELSE FALSE END FROM Invasao i")
    boolean existsAnyInvasion();
}

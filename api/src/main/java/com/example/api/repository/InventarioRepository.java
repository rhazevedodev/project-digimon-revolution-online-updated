package com.example.api.repository;

import com.example.api.model.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Inventario findByIdDigimonAndIdItem(Long idDigimon, int id);

    Optional<Inventario> findByIdItemAndIdDigimonAndIsFragmentoTrue(int idItem, Long idDigimon);
}

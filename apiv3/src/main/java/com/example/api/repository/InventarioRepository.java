package com.example.api.repository;

import com.example.api.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventarioRepository  extends JpaRepository<Inventario, Long> {
    List<Inventario> findByIdDigimon(Long idDigimon);

    List<Inventario> findByIdDigimonAndIdCategoria(Long idDigimon, int idCategoria);

    Inventario findByIdDigimonAndIdItem(Long idDigimon, int i);

}

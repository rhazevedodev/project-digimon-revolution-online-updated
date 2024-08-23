package com.example.api.service;

import com.example.api.enumerator.EnumFragmentosDigievolucao;
import com.example.api.model.entity.Inventario;
import com.example.api.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class InventarioService {

    @Autowired
    InventarioRepository inventarioRepository;
    @Autowired
    LogService logService;

    @Transactional
    public Inventario adicionarFragmentoAoInventario(Long idDigimon, EnumFragmentosDigievolucao fragmentoSorteado, int quantiaFragmentos) {
        Inventario inventario = inventarioRepository.findByIdDigimonAndIdItem(idDigimon, Integer.parseInt(fragmentoSorteado.getId()));

        if (inventario != null) {
            inventario.setQuantidade(inventario.getQuantidade() + quantiaFragmentos);
        } else {
            inventario = new Inventario();
            inventario.setIdDigimon(idDigimon);
            inventario.setIdItem(Integer.parseInt(fragmentoSorteado.getId()));
            inventario.setDescricaoItem(fragmentoSorteado.getDescricao_item());
            inventario.setQuantidade(quantiaFragmentos);
            inventario.setFragmento(true);
        }
        inventario.setDataUltimaAlteracao(LocalDateTime.now());

        inventarioRepository.save(inventario);

        logService.logAction("Adicionar Item Inventario", "Item adicionado ao inventário: " + inventario.getDescricaoItem() + " - Quantidade: " + inventario.getQuantidade());
        return inventario;

    }
}

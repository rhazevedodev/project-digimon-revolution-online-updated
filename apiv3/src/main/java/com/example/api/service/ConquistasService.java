package com.example.api.service;

import com.example.api.entity.Conquistas;
import com.example.api.entity.Digimon;
import com.example.api.enumerator.EnumConquistas;
import com.example.api.repository.ConquistasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ConquistasService {

    @Autowired
    private ConquistasRepository conquistasRepository;

    public void inicializarConquistas(Digimon digimonSelecionado) {
        // Cria uma lista de conquistas a serem inicializadas
        List<Conquistas> conquistas = Arrays.asList(
                new Conquistas((long) EnumConquistas.CONQUISTA1.getId(), EnumConquistas.CONQUISTA1.getNomeConquista(), false, 0, 1, digimonSelecionado.getIdJogador()),
                new Conquistas((long) EnumConquistas.CONQUISTA2.getId(), EnumConquistas.CONQUISTA2.getNomeConquista(), false, 0, 1, digimonSelecionado.getIdJogador()),
                new Conquistas((long) EnumConquistas.CONQUISTA3.getId(), EnumConquistas.CONQUISTA3.getNomeConquista(), false, 0, 100, digimonSelecionado.getIdJogador())
        );

        // Salva as conquistas no repositório
        conquistasRepository.saveAll(conquistas);
    }
}

package com.example.api.service;

import com.example.api.entity.Conquistas;
import com.example.api.entity.Digimon;
import com.example.api.entity.dto.ResponseListarConquistas;
import com.example.api.enumerator.EnumConquistas;
import com.example.api.repository.ConquistasRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ConquistasService {

    private ConquistasRepository conquistasRepository;

    public ConquistasService(ConquistasRepository conquistasRepository) {
        this.conquistasRepository = conquistasRepository;
    }

    public void inicializarConquistas(Digimon digimonSelecionado) {
        // Cria uma lista de conquistas a serem inicializadas
        List<Conquistas> conquistas = Arrays.asList(
                new Conquistas((long) EnumConquistas.CONQUISTA1.getId(), EnumConquistas.CONQUISTA1.getNomeConquista(), false, 0, 1, false, digimonSelecionado.getIdJogador()),
                new Conquistas((long) EnumConquistas.CONQUISTA2.getId(), EnumConquistas.CONQUISTA2.getNomeConquista(), false, 0, 1, false, digimonSelecionado.getIdJogador()),
                new Conquistas((long) EnumConquistas.CONQUISTA3.getId(), EnumConquistas.CONQUISTA3.getNomeConquista(), false, 0, 100, false, digimonSelecionado.getIdJogador()),
                new Conquistas((long) EnumConquistas.CONQUISTA4.getId(), EnumConquistas.CONQUISTA4.getNomeConquista(), false, 0, 1, false, digimonSelecionado.getIdJogador())
        );

        // Salva as conquistas no repositório
        conquistasRepository.saveAll(conquistas);
    }

    public List<ResponseListarConquistas> listarConquistasDoJogador(Long idJogador) {
        List<ResponseListarConquistas> responseList = new ArrayList<>();

        // Buscando as conquistas do jogador
        List<Conquistas> conquistasDoJogador = conquistasRepository.findByIdJogador(idJogador);

        // Mapeando as conquistas para o DTO
        for (Conquistas conquista : conquistasDoJogador) {
            ResponseListarConquistas response = new ResponseListarConquistas();

            response.setConquista(conquista);
            response.setIdConquista(EnumConquistas.getIdConquistaByNomeConquista(conquista.getNomeConquista())); // Supondo que Conquistas tem um método getId()
            response.setDescricaoConquista(EnumConquistas.getDescricaoConquistaByNomeConquista(conquista.getNomeConquista())); // Supondo que Conquistas tem um método getDescricao()
            response.setImagemConquista(EnumConquistas.getImagemConquistaByNomeConquista(conquista.getNomeConquista())); // Supondo que Conquistas tem um método getImagem()
            response.setTipoRecompensa(EnumConquistas.getTipoRecompensaByNomeConquista(conquista.getNomeConquista())); // Supondo que Conquistas tem um método getTipoRecompensa()
            response.setQuantidadeRecompensa(EnumConquistas.getQuantidadeRecompensaByNomeConquista(conquista.getNomeConquista())); // Supondo que Conquistas tem um método getQuantidadeRecompensa()

            responseList.add(response);
        }

        return responseList;
    }

    public boolean verificarSeConquistasForamInicializadas(Long idJogador) {
        return conquistasRepository.existsByIdJogador(idJogador);
    }
}
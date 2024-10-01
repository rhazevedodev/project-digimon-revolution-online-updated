package com.example.api.service;

import com.example.api.entity.Digievolucao;
import com.example.api.entity.Digimon;
import com.example.api.entity.Inventario;
import com.example.api.entity.dto.ResponseListarDigievolucoes;
import com.example.api.enumerator.*;
import com.example.api.repository.DigievolucaoRepository;
import com.example.api.repository.DigimonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DigievolucaoService {

    @Autowired
    private DigimonRepository digimonRepository;

    @Autowired
    private DigievolucaoRepository digievolucaoRepository;

    @Autowired
    private DigimonService digimonService;
    @Autowired
    private InventarioService inventarioService;

    public List<ResponseListarDigievolucoes> getEvolucoes(int idDigimon) {
        EnumDigimonRookie rookie = EnumDigimonRookie.getEnumById(idDigimon);
        if (rookie != null) {
            List<Digievolucao> lista = digievolucaoRepository.findByDigimonRookie(Long.valueOf(idDigimon));
            if (lista.isEmpty()) {
                throw new RuntimeException("Nenhuma evolução encontrada para o Digimon solicitado.");
            }
            List<Inventario> inventarioFragmentos = inventarioService.getInventarioDoJogador(Long.valueOf(4), 4);
            List<ResponseListarDigievolucoes> evolucoesPossiveis = new ArrayList<>();
            for (Digievolucao evolucao : lista) {
                ResponseListarDigievolucoes response = getResponseListarDigievolucoes(evolucao, inventarioFragmentos);
                evolucoesPossiveis.add(response);
            }
            return evolucoesPossiveis;
        } else {
            throw new RuntimeException("Digimon Rookie não encontrado.");
        }
    }

    private static ResponseListarDigievolucoes getResponseListarDigievolucoes(Digievolucao evolucao, List<Inventario> inventarioFragmentos) {
        ResponseListarDigievolucoes response = new ResponseListarDigievolucoes();
        response.setIdDigimonOrigem(evolucao.getDigimonRookie());
        EnumDigimonRookie rookie = EnumDigimonRookie.getEnumById(evolucao.getDigimonRookie());
        response.setDigimonOrigem(rookie.getDescricao());
        if (evolucao.getDigimonMega() != 0l) {
            response.setIdDigimonDestino(evolucao.getDigimonMega());
            EnumDigimonMega mega = EnumDigimonMega.getEnumById(evolucao.getDigimonMega());
            response.setDigimonDestino(mega.getDescricao());
            response.setPathImagemDigimonDestino(mega.getUrlImg());
            response.setFragmentosDisponiveis(getQuantidadeFragmentos(inventarioFragmentos, evolucao.getDigimonMega(), "MEGA"));
        }
        if (evolucao.getDigimonUltimate() != 0l) {
            response.setIdDigimonDestino(evolucao.getDigimonUltimate());
            EnumDigimonUltimate ultimate = EnumDigimonUltimate.getEnumById(evolucao.getDigimonUltimate());
            response.setDigimonDestino(ultimate.getDescricao());
            response.setPathImagemDigimonDestino(ultimate.getUrlImg());
            response.setFragmentosDisponiveis(getQuantidadeFragmentos(inventarioFragmentos, evolucao.getDigimonUltimate(), "ULTIMATE"));

        }
        if (evolucao.getDigimonChampion() != 0l) {
            response.setIdDigimonDestino(evolucao.getDigimonChampion());
            EnumDigimonChampion champion = EnumDigimonChampion.getEnumById(evolucao.getDigimonChampion());
            response.setDigimonDestino(champion.getDescricao());
            response.setPathImagemDigimonDestino(champion.getUrlImg());
            response.setFragmentosDisponiveis(getQuantidadeFragmentos(inventarioFragmentos, evolucao.getDigimonChampion(), "CHAMPION"));
        }
        response.setFragmentosNecessarios(evolucao.getFragmentosNecessarios());
        response.setItemEspecialNecessario(evolucao.isItemEspecialNecessario());
//                response.setIdItemEspecial(evolucao.get());
        response.setItemEspecial(evolucao.getItemEspecial());
        return response;
    }

    private static int getQuantidadeFragmentos(List<Inventario> inventarioFragmentos, long idDigimon, String tipo) {
        String descricao = null;
        String descricaoItem = null;

        switch (tipo) {
            case "CHAMPION":
                descricao = EnumDigimonChampion.getDescricaoById((int) idDigimon);
                break;
            case "ULTIMATE":
                descricao = EnumDigimonUltimate.getDescricaoById((int) idDigimon);
                break;
            case "MEGA":
                descricao = EnumDigimonMega.getDescricaoById((int) idDigimon);
                break;
            default:
                throw new IllegalArgumentException("Tipo inválido: " + tipo);
        }

        System.out.println(tipo.toLowerCase() + ": " + descricao);

        if (descricao != null) {
            try {
                EnumFragmentosDigievolucao fragmento = EnumFragmentosDigievolucao.valueOf(descricao.toUpperCase());
                descricaoItem = EnumFragmentosDigievolucao.getDescricaoItemByEnum(fragmento);
                System.out.println("Descrição do item: " + descricaoItem);
            } catch (IllegalArgumentException e) {
                System.out.println("Fragmento não encontrado para o " + tipo.toLowerCase() + ": " + descricao);
            }
        } else {
            System.out.println("Descrição do " + tipo.toLowerCase() + " não encontrada para o id: " + idDigimon);
        }

        for (Inventario inventario : inventarioFragmentos) {
            if (descricaoItem != null && descricaoItem.equals(inventario.getDescricaoItem())) {
                return inventario.getQuantidade();
            }
        }

        return 0;
    }
}
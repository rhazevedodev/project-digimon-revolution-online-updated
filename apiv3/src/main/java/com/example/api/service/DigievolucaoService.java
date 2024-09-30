package com.example.api.service;

import com.example.api.entity.Digievolucao;
import com.example.api.entity.Digimon;
import com.example.api.entity.dto.ResponseListarDigievolucoes;
import com.example.api.enumerator.EnumDigimonChampion;
import com.example.api.enumerator.EnumDigimonMega;
import com.example.api.enumerator.EnumDigimonRookie;
import com.example.api.enumerator.EnumDigimonUltimate;
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

    public List<ResponseListarDigievolucoes> getEvolucoes(int idDigimon) {
        EnumDigimonRookie rookie = EnumDigimonRookie.getEnumById(idDigimon);
        if (rookie != null) {
            List<Digievolucao> lista = digievolucaoRepository.findByDigimonRookie(Long.valueOf(idDigimon));
            if (lista.isEmpty()) {
                throw new RuntimeException("Nenhuma evolução encontrada para o Digimon solicitado.");
            }
            List<ResponseListarDigievolucoes> responseList = new ArrayList<>();
            for (Digievolucao evolucao : lista) {
                ResponseListarDigievolucoes response = getResponseListarDigievolucoes(evolucao);
                responseList.add(response);
            }
            return responseList;
        } else {
            throw new RuntimeException("Digimon Rookie não encontrado.");
        }
    }

    private static ResponseListarDigievolucoes getResponseListarDigievolucoes(Digievolucao evolucao) {
        ResponseListarDigievolucoes response = new ResponseListarDigievolucoes();
        response.setIdDigimonOrigem(evolucao.getDigimonRookie());
        EnumDigimonRookie rookie = EnumDigimonRookie.getEnumById(evolucao.getDigimonRookie());
        response.setDigimonOrigem(rookie.getDescricao());
        if(evolucao.getDigimonMega() != 0l) {
            response.setIdDigimonDestino(evolucao.getDigimonMega());
            EnumDigimonMega mega = EnumDigimonMega.getEnumById(evolucao.getDigimonMega());
            response.setDigimonDestino(mega.getDescricao());
            response.setPathImagemDigimonDestino(mega.getUrlImg());
        }
        if(evolucao.getDigimonUltimate() != 0l) {
            response.setIdDigimonDestino(evolucao.getDigimonUltimate());
            EnumDigimonUltimate ultimate = EnumDigimonUltimate.getEnumById(evolucao.getDigimonUltimate());
            response.setDigimonDestino(ultimate.getDescricao());
            response.setPathImagemDigimonDestino(ultimate.getUrlImg());
        }
        if(evolucao.getDigimonChampion() != 0l) {
            response.setIdDigimonDestino(evolucao.getDigimonChampion());
            EnumDigimonChampion champion = EnumDigimonChampion.getEnumById(evolucao.getDigimonChampion());
            response.setDigimonDestino(champion.getDescricao());
            response.setPathImagemDigimonDestino(champion.getUrlImg());
        }
        response.setFragmentosNecessarios(evolucao.getFragmentosNecessarios());
        response.setItemEspecialNecessario(evolucao.isItemEspecialNecessario());
//                response.setIdItemEspecial(evolucao.get());
        response.setItemEspecial(evolucao.getItemEspecial());
        return response;
    }

}

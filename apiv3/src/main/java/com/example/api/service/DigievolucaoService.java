package com.example.api.service;

import com.example.api.entity.AtributosModificadores;
import com.example.api.entity.Digievolucao;
import com.example.api.entity.Digimon;
import com.example.api.entity.Inventario;
import com.example.api.entity.dto.RequestDigievolucao;
import com.example.api.entity.dto.ResponseListarDigievolucoes;
import com.example.api.enumerator.*;
import com.example.api.repository.DigievolucaoRepository;
import com.example.api.repository.DigimonRepository;
import com.example.api.utils.ModificadoresChampion;
import com.example.api.utils.ModificadoresMega;
import com.example.api.utils.ModificadoresUltimate;
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

    public List<ResponseListarDigievolucoes> getEvolucoes(int idDigimonUsuario) {
        Digimon digimon = digimonService.getDigimonById((long) idDigimonUsuario);
        String proxTier = digimonService.getProxTierDigimon((long) idDigimonUsuario);
        String tierAtual = null;

        switch (proxTier) {
            case "Champion":
                tierAtual = "Rookie";
                break;
            case "Ultimate":
                tierAtual = "Champion";
                break;
            case "Mega":
                tierAtual = "Ultimate";
                break;
            case "Jogress":
                tierAtual = "Mega";
                break;
        }

        if (tierAtual == null) {
            throw new RuntimeException("Tier atual não encontrado.");
        }

        List<Digievolucao> lista = new ArrayList<>();
        List<Inventario> inventarioFragmentos = inventarioService.getInventarioDoJogador(Long.valueOf(idDigimonUsuario), 4);
        List<ResponseListarDigievolucoes> evolucoesPossiveis = new ArrayList<>();

        switch (tierAtual) {
            case "Rookie":
                EnumDigimonRookie rookie = EnumDigimonRookie.getEnumById(digimon.getIdRookie());
                if (rookie != null) {
                    lista = digievolucaoRepository.findByDigimonRookie(Long.valueOf(digimon.getIdRookie()));
                } else {
                    throw new RuntimeException("Digimon Rookie não encontrado.");
                }
                break;
            case "Champion":
                EnumDigimonChampion champion = EnumDigimonChampion.getEnumById(digimon.getIdChampion());
                if (champion != null) {
                    lista = digievolucaoRepository.findByDigimonChampionAndUltimateNotZero(Long.valueOf(digimon.getIdChampion()));
                } else {
                    throw new RuntimeException("Digimon Champion não encontrado.");
                }
                break;
            case "Ultimate":
                EnumDigimonUltimate ultimate = EnumDigimonUltimate.getEnumById(digimon.getIdUltimate());
                if (ultimate != null) {
                    lista = digievolucaoRepository.findByDigimonUltimateAndMegaNotZero(Long.valueOf(digimon.getIdUltimate()));
                } else {
                    throw new RuntimeException("Digimon Ultimate não encontrado.");
                }
                break;
            case "Mega":
                EnumDigimonMega mega = EnumDigimonMega.getEnumById(digimon.getIdMega());
                if (mega != null) {
                    lista = digievolucaoRepository.findByDigimonMega(Long.valueOf(digimon.getIdMega()));
                } else {
                    throw new RuntimeException("Digimon Mega não encontrado.");
                }
                break;
        }

        if (lista.isEmpty()) {
            throw new RuntimeException("Nenhuma evolução encontrada para o Digimon solicitado.");
        }

        for (Digievolucao evolucao : lista) {
            ResponseListarDigievolucoes response = getResponseListarDigievolucoes(evolucao, inventarioFragmentos);
            evolucoesPossiveis.add(response);
        }

        return evolucoesPossiveis;
    }

    private static ResponseListarDigievolucoes getResponseListarDigievolucoes(Digievolucao evolucao, List<Inventario> inventarioFragmentos) {
        ResponseListarDigievolucoes response = new ResponseListarDigievolucoes();

        //ROOKIE -> CHAMPION
        if((evolucao.getDigimonRookie() != 0) && (evolucao.getDigimonChampion() != 0)){
            //ROOKIE
            response.setIdDigimonOrigem(evolucao.getDigimonRookie());
            EnumDigimonRookie rookie = EnumDigimonRookie.getEnumById(evolucao.getDigimonRookie());
            response.setDigimonOrigem(rookie.getDescricao());

            //CHAMPION
            response.setIdDigimonDestino(evolucao.getDigimonChampion());
            EnumDigimonChampion champion = EnumDigimonChampion.getEnumById(evolucao.getDigimonChampion());
            response.setDigimonDestino(champion.getDescricao());
            response.setPathImagemDigimonDestino(champion.getUrlImg());
            response.setFragmentosDisponiveis(getQuantidadeFragmentos(inventarioFragmentos, evolucao.getDigimonChampion(), "CHAMPION"));
        }
        //CHAMPION -> ULTIMATE
        if((evolucao.getDigimonChampion() != 0) && (evolucao.getDigimonUltimate() != 0)){
            //CHAMPION
            response.setIdDigimonOrigem(evolucao.getDigimonChampion());
            EnumDigimonChampion champion = EnumDigimonChampion.getEnumById(evolucao.getDigimonChampion());
            response.setDigimonOrigem(champion.getDescricao());

            //ULTIMATE
            response.setIdDigimonDestino(evolucao.getDigimonUltimate());
            EnumDigimonUltimate ultimate = EnumDigimonUltimate.getEnumById(evolucao.getDigimonUltimate());
            response.setDigimonDestino(ultimate.getDescricao());
            response.setPathImagemDigimonDestino(ultimate.getUrlImg());
            response.setFragmentosDisponiveis(getQuantidadeFragmentos(inventarioFragmentos, evolucao.getDigimonUltimate(), "ULTIMATE"));
        }
        //ULTIMATE -> MEGA
        if((evolucao.getDigimonUltimate() != 0) && (evolucao.getDigimonMega() != 0)){
            //ULTIMATE
            response.setIdDigimonOrigem(evolucao.getDigimonUltimate());
            EnumDigimonUltimate ultimate = EnumDigimonUltimate.getEnumById(evolucao.getDigimonUltimate());
            response.setDigimonOrigem(ultimate.getDescricao());

            //MEGA
            response.setIdDigimonDestino(evolucao.getDigimonMega());
            EnumDigimonMega mega = EnumDigimonMega.getEnumById(evolucao.getDigimonMega());
            response.setDigimonDestino(mega.getDescricao());
            response.setPathImagemDigimonDestino(mega.getUrlImg());
            response.setFragmentosDisponiveis(getQuantidadeFragmentos(inventarioFragmentos, evolucao.getDigimonMega(), "MEGA"));
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

    public void digievolucao(RequestDigievolucao request) {
        try {
            Digimon digimon = digimonService.getDigimonById((long) request.getIdDigimon());
            if (digimon == null) {
                throw new RuntimeException("Digimon não encontrado.");
            }
            String tierEvolucao = digimonService.getProxTierDigimon((long) request.getIdDigimon());
            int idDigievolucao = 0;
            if (tierEvolucao.equals("Champion")) {
                idDigievolucao = EnumDigimonChampion.getIdByDescricao(request.getEvolucaoEscolhida());
            }
            if (tierEvolucao.equals("Ultimate")) {
                idDigievolucao = EnumDigimonUltimate.getIdByDescricao(request.getEvolucaoEscolhida());
            }
            if (tierEvolucao.equals("Mega")) {
                idDigievolucao = EnumDigimonMega.getIdByDescricao(request.getEvolucaoEscolhida());
            }
            String descricaoEvolucao = EnumFragmentosDigievolucao.getDescricaoItemByEnum(EnumFragmentosDigievolucao.valueOf(request.getEvolucaoEscolhida().toUpperCase()));

            int fragmentosNecessarios = request.getFragmentosNecessarios();
            Inventario inventarioInicioTransacao = inventarioService.carregarInventarioPorIdeDescricaoitem((long) 4, descricaoEvolucao);
            int fragmentosDisponiveis = inventarioInicioTransacao.getQuantidade();
            if (fragmentosDisponiveis < fragmentosNecessarios) {
                throw new RuntimeException("Quantidade de fragmentos insuficiente.");
            }
            int fragmentosFinalTransacao = fragmentosDisponiveis - fragmentosNecessarios;


            AtributosModificadores modificadoresAntes = digimon.getAtributosModificadores();
            AtributosModificadores modificadoresAtualizados = null;
            if (tierEvolucao.equals("Champion")) {
                modificadoresAtualizados = ModificadoresChampion.definirModificadoresChampion(modificadoresAntes, EnumDigimonChampion.valueOf(request.getEvolucaoEscolhida()));
                digimon.setIdChampion(idDigievolucao);
            }
            if (tierEvolucao.equals("Ultimate")) {
                modificadoresAtualizados = ModificadoresUltimate.definirModificadoresUltimate(modificadoresAntes, EnumDigimonUltimate.valueOf(request.getEvolucaoEscolhida().toUpperCase()));
                digimon.setIdUltimate(idDigievolucao);
            }
            if (tierEvolucao.equals("Mega")) {
                modificadoresAtualizados = ModificadoresMega.definirModificadoresMega(modificadoresAntes, EnumDigimonMega.valueOf(request.getEvolucaoEscolhida().toUpperCase()));
                digimon.setIdMega(idDigievolucao);
            }
            digimon.setAtributosModificadores(modificadoresAtualizados);
            digimonService.save(digimon);

            if (fragmentosFinalTransacao == 0) {
                inventarioService.delete(inventarioInicioTransacao);
            } else {
                inventarioInicioTransacao.setQuantidade(fragmentosFinalTransacao);
                inventarioService.save(inventarioInicioTransacao);
            }

        } catch (Exception e){
            throw new RuntimeException("Erro ao realizar a digievolução: " + e.getMessage());
        }
    }
}
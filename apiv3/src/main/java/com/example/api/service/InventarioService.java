package com.example.api.service;

import com.example.api.entity.Digimon;
import com.example.api.entity.Inventario;
import com.example.api.entity.Jogador;
import com.example.api.enumerator.EnumCategoriasInventario;
import com.example.api.repository.InventarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventarioService {

    private static final Logger logger = LoggerFactory.getLogger(StatusService.class);

    private final DigimonService digimonService;
    private final JogadorService jogadorService;
    private final RegistroConquistasService registroConquistasService;
    private final PremiumService premiumService;
    private final InventarioRepository inventarioRepository;

    public InventarioService(DigimonService digimonService, JogadorService jogadorService, RegistroConquistasService registroConquistasService, PremiumService premiumService, InventarioRepository inventarioRepository) {
        this.digimonService = digimonService;
        this.jogadorService = jogadorService;
        this.registroConquistasService = registroConquistasService;
        this.premiumService = premiumService;
        this.inventarioRepository = inventarioRepository;
    }

    public Map<String, Object> carregarTelaInventario(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();

        Digimon digimon = digimonService.getDigimonById(idDigimon);
        Jogador jogador = jogadorService.getJogadorById(digimon.getIdJogador());

        // Chama os métodos existentes e adiciona os resultados ao response
        response.putAll(digimonService.carregarImagemDigimon(idDigimon));
        response.putAll(premiumService.carregarInformacoesPremium(idDigimon));
        response.put("nivel", digimon.getNivel());
        response.put("vida", digimon.getAtributos().getPontosVida());
        response.put("energia", digimon.getAtributos().getPontosEnergia());

        return response;
    }

    public Map<String, Object> carregarInventario(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();

        List<Inventario> inventarioList = inventarioRepository.findByIdDigimon(idDigimon);

        // Verifica se a lista está vazia
        if (inventarioList.isEmpty()) {
            response.put("message", "Nenhum item encontrado para o Digimon com ID " + idDigimon);
            return response;
        }

        // Cria uma lista para armazenar os itens do inventário
        List<Map<String, Object>> itens = new ArrayList<>();

        // Itera sobre cada item do inventário
        for (Inventario inventario : inventarioList) {
            Map<String, Object> itemDetails = new LinkedHashMap<>();
            itemDetails.put("urlImagem","/imagens/icones/pocaoVida.png");
            itemDetails.put("idInventario", inventario.getId());
            itemDetails.put("idDigimon", inventario.getIdDigimon());
            itemDetails.put("nomeItem", inventario.getNomeItem());
            itemDetails.put("descricaoItem", inventario.getDescricaoItem());
            itemDetails.put("categoriaItem", EnumCategoriasInventario.getNomeCategoriaById(inventario.getIdCategoria()));
            itemDetails.put("quantidade", inventario.getQuantidade());
            itemDetails.put("idItem", inventario.getIdItem());

            // Adiciona os detalhes do item à lista
            itens.add(itemDetails);
        }

        // Adiciona a lista de itens ao mapa de resposta
        response.put("itens", itens);

        return response;
    }
}

package com.example.api.service;

import com.example.api.entity.Digimon;
import com.example.api.entity.Inventario;
import com.example.api.entity.Jogador;
import com.example.api.enumerator.EnumCategoriasInventario;
import com.example.api.enumerator.EnumFragmentosDigievolucao;
import com.example.api.repository.InventarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
//        response.put("nivel", digimon.getNivel());
//        response.put("vida", digimon.getAtributos().getPontosVida());
//        response.put("energia", digimon.getAtributos().getPontosEnergia());

        return response;
    }

    public Inventario carregarInventarioPorIdeDescricaoitem(Long idDigimon, String descricaoItem) {
        return inventarioRepository.findByIdDigimonAndDescricaoItem(idDigimon, descricaoItem);
    }

    public Map<String, Object> carregarInventario(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();

        List<Inventario> inventarioList = inventarioRepository.findByIdDigimon(idDigimon);

        // Verifica se a lista está vazia
        if (inventarioList.isEmpty()) {
            response.put("message", "Nenhum item encontrado para esse Digimon");
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

    public List<Inventario> getInventarioDoJogador(Long idDigimon,int idCategoria) {
        return inventarioRepository.findByIdDigimonAndIdCategoria(idDigimon,idCategoria);
    }

    @Transactional
    public Inventario adicionarFragmentoAoInventario(Long idDigimon, EnumFragmentosDigievolucao fragmentoSorteado, int quantiaFragmentos) {
        Inventario inventario = inventarioRepository.findByIdDigimonAndIdItem(idDigimon, Integer.parseInt(fragmentoSorteado.getId()));

        if (inventario != null) {
            inventario.setQuantidade(inventario.getQuantidade() + quantiaFragmentos);
        } else {
            inventario = new Inventario();
            inventario.setDescricaoItem(fragmentoSorteado.getDescricao_item());
            inventario.setEquipado(false);
            inventario.setIdCategoria(4);
            inventario.setIdDigimon(idDigimon);
            inventario.setIdItem(Integer.parseInt(fragmentoSorteado.getId()));
            inventario.setPodeTrocar(false);
            inventario.setPodeVender(false);
            inventario.setQuantidade(quantiaFragmentos);
            inventario.setNomeItem(fragmentoSorteado.getDescricao_item());
            inventario.setValorCompra(0);
            inventario.setValorVenda(0);
        }
        inventario.setDataUltimaAlteracao(LocalDateTime.now());

        inventarioRepository.save(inventario);

        return inventario;

    }

    @Transactional
    public void delete(Inventario inventario) {
        inventarioRepository.delete(inventario);
    }

    @Transactional
    public void save(Inventario inventario) {
        inventarioRepository.save(inventario);
    }
}

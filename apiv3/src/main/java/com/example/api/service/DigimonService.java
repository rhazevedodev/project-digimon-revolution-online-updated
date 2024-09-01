package com.example.api.service;

import com.example.api.entity.Atributos;
import com.example.api.entity.AtributosElementos;
import com.example.api.entity.Digimon;
import com.example.api.enumerator.EnumDigimonRookie;
import com.example.api.enumerator.EnumElementos;
import com.example.api.repository.DigimonRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DigimonService {

    private DigimonRepository digimonRepository;
    private JogadorService jogadorService;

    public DigimonService(DigimonRepository digimonRepository, JogadorService jogadorService) {
        this.digimonRepository = digimonRepository;
        this.jogadorService = jogadorService;
    }

    public boolean getDigimonByIdJogador(int idJogador) {
        return digimonRepository.existsByIdJogador(idJogador);

    }

    public String getIdByDescricao(String descricao) {
        return EnumDigimonRookie.getIdByDescricao(descricao);
    }

    public boolean getNomeDigimon(String nomeDigimon) {
        return digimonRepository.existsByNome(nomeDigimon);
    }

    public Digimon selecionarDigimon(Digimon digimonSelecionado) {
        if (!jogadorService.verificarJogadorExistente(digimonSelecionado.getIdJogador())) {
            throw new RuntimeException("Jogador não encontrado");
        }
        if (digimonSelecionado.getIdRookie() <= 0) {
            throw new RuntimeException("o parametro idRookie precisa ser maior que 0");
        }

        EnumDigimonRookie rookie = EnumDigimonRookie.getEnumById(digimonSelecionado.getIdRookie());
        String nomeJogador = jogadorService.getNomeJogador(digimonSelecionado.getIdJogador());

        String elementoRookie = EnumDigimonRookie.getElementoByEnum(rookie);

        int idElementoPrimitivoRookie = EnumElementos.getIdElemento(elementoRookie);

        Atributos atributos = new Atributos();
        digimonSelecionado.setAtributos(atributos);

        AtributosElementos atributosElementos = new AtributosElementos();
        atributosElementos.setElementoPrimitivo(idElementoPrimitivoRookie);
        atributosElementos.setPontosElementoPrimitivo(1);
        digimonSelecionado.setAtributosElementos(atributosElementos);

        return digimonRepository.save(digimonSelecionado);
    }

    public Map<String, Object> carregarImagemDigimon(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();
        Digimon digimon = getDigimonById(idDigimon);
        String urlImg = EnumDigimonRookie.getUrlImgById(digimon.getIdRookie()).toLowerCase();
        response.put("url_imagem_digimon", urlImg);
        return response;
    }

    public List<Digimon> getDigimonByUsuario(String nomeUsuario) {
        return digimonRepository.getDigimonByIdJogador(jogadorService.getIdByUsuario(nomeUsuario));
    }

    public Digimon getDigimonById(Long idDigimon) {
        return digimonRepository.findById(idDigimon)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));
    }

    public Long getIdJogadorByDigimonId(Long idDigimon) {
        return digimonRepository.findById(idDigimon)
                .map(Digimon::getIdJogador)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));
    }
}

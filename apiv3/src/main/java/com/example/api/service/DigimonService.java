package com.example.api.service;

import com.example.api.entity.Atributos;
import com.example.api.entity.AtributosElementos;
import com.example.api.entity.Digimon;
import com.example.api.enumerator.EnumDigimonRookie;
import com.example.api.enumerator.EnumElementos;
import com.example.api.repository.DigimonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Digimon> getDigimonByUsuario(String nomeUsuario) {
        return digimonRepository.getDigimonByIdJogador(jogadorService.getIdByUsuario(nomeUsuario));
    }
}

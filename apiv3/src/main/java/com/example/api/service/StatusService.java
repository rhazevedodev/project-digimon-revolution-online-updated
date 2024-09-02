package com.example.api.service;

import com.example.api.controller.StatusController;
import com.example.api.entity.Digimon;
import com.example.api.entity.Jogador;
import com.example.api.enumerator.*;
import com.example.api.utils.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class StatusService {

    private static final Logger logger = LoggerFactory.getLogger(StatusService.class);

    private final DigimonService digimonService;
    private final JogadorService jogadorService;
    private final RegistroConquistasService registroConquistasService;
    private final PremiumService premiumService;

    public StatusService(DigimonService digimonService, JogadorService jogadorService, RegistroConquistasService registroConquistasService, PremiumService premiumService) {
        this.digimonService = digimonService;
        this.jogadorService = jogadorService;
        this.registroConquistasService = registroConquistasService;
        this.premiumService = premiumService;
    }

    public Map<String, Object> carregarTelaStatus(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();

        Digimon digimon = digimonService.getDigimonById(idDigimon);
        Jogador jogador = jogadorService.getJogadorById(digimon.getIdJogador());

        // Chama os métodos existentes e adiciona os resultados ao response
        response.putAll(digimonService.carregarImagemDigimon(idDigimon));
        response.putAll(premiumService.carregarInformacoesPremium(idDigimon));
        response.putAll(carregarInformacoes(digimon, jogador));
        response.putAll(carregarAtributos(digimon, jogador));
        response.putAll(carregarEstatisticas(idDigimon));

        return response;
    }


    public Map<String, Object> carregarInformacoes(Digimon digimon, Jogador jogador){
        Map<String, Object> response = new LinkedHashMap<>();

        String dataJogoDesde = Data.formatarDataCadastroParaTelaStatus(jogador.getCriadoEm());

        String indicacao =  (jogador.getIndicacao() != null) ? jogador.getIndicacao() : "Sem indicação";

        int reservaBits = digimon.getBits();
        int reservaDiamantes = digimon.getDiamantes();

        String apelidoDigimon = digimon.getNome();

        String digimonRookie = EnumDigimonRookie.getDescricaoById(digimon.getIdRookie());
        String digimonChampion = (digimon.getIdChampion() != 0) ? EnumDigimonChampion.getDescricaoById(digimon.getIdChampion()) : "-";
        String digimonUltimate = (digimon.getIdUltimate() != 0) ? EnumDigimonUltimate.getDescricaoById(digimon.getIdUltimate()) : "-";
        String digimonMega = (digimon.getIdMega() != 0) ? EnumDigimonMega.getDescricaoById(digimon.getIdMega()) : "-";

        String tierDigimon = determinarTier(digimon);
        int nivelDigimon = digimon.getNivel();

        preencherResponseInformacoes(response, dataJogoDesde, indicacao, reservaBits, reservaDiamantes, apelidoDigimon, digimonRookie, digimonChampion, digimonUltimate, digimonMega, tierDigimon, nivelDigimon);

        return response;
    }

    public Map<String, Object> carregarAtributos(Digimon digimon, Jogador jogador){
        Map<String, Object> response = new LinkedHashMap<>();
        int forca = digimon.getAtributos().getPontosForca();
        int inteligencia = digimon.getAtributos().getPontosInteligencia();
        int conhecimento = digimon.getAtributos().getPontosConhecimento();
        int agilidade = digimon.getAtributos().getPontosAgilidade();
        int energia = digimon.getAtributos().getPontosEnergia();
        int vida = digimon.getAtributos().getPontosVida();
        int experiencia = digimon.getPontosExperiencia();
        int experienciaNecessaria = EnumNivelDigimon.getExperienciaNecessaria(digimon.getNivel());
        preencherResponseAtributos(response, forca, inteligencia, conhecimento, agilidade, energia, vida, experiencia, experienciaNecessaria);
        return response;
    }

    public Map<String, Object> carregarEstatisticas(Long idDigimon){
        return registroConquistasService.carregarConquistas(idDigimon);
    }

    private String determinarTier(Digimon digimon) {
        if (digimon.getIdMega() != 0) {
            return "Mega";
        } else if (digimon.getIdUltimate() != 0) {
            return "Ultimate";
        } else if (digimon.getIdChampion() != 0) {
            return "Champion";
        } else {
            return "Rookie";
        }
    }

    private void preencherResponseAtributos(Map<String, Object> response, int forca, int inteligencia, int conhecimento, int agilidade, int energia, int vida, int experiencia, int experienciaNecessaria) {
        response.put("forca", forca);
        response.put("inteligencia", inteligencia);
        response.put("conhecimento", conhecimento);
        response.put("agilidade", agilidade);
        response.put("energia", energia);
        response.put("vida", vida);
        response.put("experiencia", experiencia);
        response.put("experienciaNecessaria", experienciaNecessaria);
    }

    private void preencherResponseInformacoes(Map<String, Object> response, String dataJogoDesde, String indicacao, int reservaBits, int reservaDiamantes, String apelidoDigimon, String digimonRookie, String digimonChampion, String digimonUltimate, String digimonMega, String tierDigimon, int nivelDigimon) {
        response.put("dataJogoDesde", dataJogoDesde);
        response.put("indicacao", indicacao);
        response.put("reservaBits", reservaBits);
        response.put("reservaDiamantes", reservaDiamantes);
        response.put("apelidoDigimon", apelidoDigimon);
        response.put("digimonRookie", digimonRookie);
        response.put("digimonChampion", digimonChampion);
        response.put("digimonUltimate", digimonUltimate);
        response.put("digimonMega", digimonMega);
        response.put("tierDigimon", tierDigimon);
        response.put("nivelDigimon", nivelDigimon);
    }

}

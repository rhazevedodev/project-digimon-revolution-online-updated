package com.example.api.service.telas;

import com.example.api.enumerator.EnumDigimonRookie;
import com.example.api.model.entity.Cacada;
import com.example.api.model.entity.Digimon;
import com.example.api.service.CacadaService;
import com.example.api.service.DigimonService;
import com.example.api.service.PremiumService;
import com.example.api.service.auxiliar.AuxDigimonService;
import com.example.api.service.auxiliar.AuxPremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TelaCacadasService {

    @Autowired
    private DigimonService digimonService;
    @Autowired
    private PremiumService premiumService;
    @Autowired
    private CacadaService cacadaService;
    @Autowired
    private AuxPremiumService auxPremiumService;
    @Autowired
    private AuxDigimonService auxDigimonService;

    /**
     * Carrega as informações da tela de caçadas para um Digimon específico.
     *
     * @param idDigimon o ID do Digimon
     * @return um mapa contendo as informações da tela de caçadas
     */
    public Map<String, Object> carregarTelaCacadas(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();

        adicionarInformacoesCacada(idDigimon, response);
        auxPremiumService.carregarDadosPremium(idDigimon, response);
        auxDigimonService.carregarDadosDigimonGeral(idDigimon, response);

        return response;
    }

    /**
     * Adiciona informações relacionadas à caça ao mapa de resposta.
     *
     * @param idDigimon o ID do Digimon
     * @param response o mapa onde as informações serão adicionadas
     */
    private void adicionarInformacoesCacada(Long idDigimon, Map<String, Object> response) {
        int tempoDisponivel = cacadaService.verificarTempoDisponivelCacada(idDigimon);
        boolean emAndamento = cacadaService.verificarCacadaEmAndamento(idDigimon);
        String horaResgate = cacadaService.buscarTempoResgateCacadaEmAndamento(idDigimon);
        boolean resgateDisponivel = cacadaService.validarResgateDisponivelCacada(idDigimon);

        response.put("tempoDisponivel", tempoDisponivel);
        response.put("emAndamento", emAndamento);
        response.put("horaResgate", horaResgate);
        response.put("resgateDisponivel", resgateDisponivel);
    }
}

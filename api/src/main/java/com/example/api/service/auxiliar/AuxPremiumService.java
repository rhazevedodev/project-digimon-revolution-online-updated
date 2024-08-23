package com.example.api.service.auxiliar;

import com.example.api.service.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AuxPremiumService {

    @Autowired
    private PremiumService premiumService;

    /**
     * Método responsável por carregar os dados premium nas telas
     * @param idDigimon ID do Digimon
     * @return Map contendo os dados premium
     */
    public Map<String,Object> carregarDadosPremium(Long idDigimon, Map<String, Object> response){

        // Validar e carregar informações premium
        String statusPremium = premiumService.validarPremium(idDigimon);
        if (statusPremium == null) {
            response.put("status_premium", "Não disponível");
            response.put("data_inicio_premium", null);
            response.put("data_fim_premium", null);
            return response;
        }

        Map<String, Object> premium = premiumService.buscarPeriodoPremium(idDigimon);
        if (premium == null) {
            response.put("status_premium", "Não disponível");
            response.put("data_inicio_premium", null);
            response.put("data_fim_premium", null);
            return response;
        }

        response.put("status_premium", statusPremium);
        response.put("data_inicio_premium", premium.get("dataInicio"));
        response.put("data_fim_premium", premium.get("dataFim"));
        return response;
    }

}

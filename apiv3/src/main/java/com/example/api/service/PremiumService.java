package com.example.api.service;

import com.example.api.entity.Digimon;
import com.example.api.entity.Premium;
import com.example.api.enumerator.EnumDigimonRookie;
import com.example.api.repository.PremiumRepository;
import com.example.api.utils.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PremiumService {

    private final PremiumRepository premiumRepository;
    private final DigimonService digimonService;

    public PremiumService(PremiumRepository premiumRepository, DigimonService digimonService) {
        this.premiumRepository = premiumRepository;
        this.digimonService = digimonService;
    }

    public Map<String, Object> carregarInformacoesPremium(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.putAll(buscarPeriodoPremium(idDigimon));
        return response;
    }

    public String validarPremium(Long idDigimon) {
        Optional<Premium> premium = premiumRepository.findLatestByIdDigimon(idDigimon);
        if (premium.isPresent()) {
            if (premium.get().getDataFim().isAfter(LocalDateTime.now())) {
                return "Ativo";
            }
        }
        return "Inativo";
    }

    public Map<String, Object> buscarPeriodoPremium(Long idDigimon) {
        Optional<Premium> premium = premiumRepository.findLatestByIdDigimon(idDigimon);
        Map<String, Object> response = new LinkedHashMap<>();

        if (premium.isPresent()) {
            String dataInicio = Data.formatarDataCadastroParaTelaStatus(premium.get().getDataInicio());
            String dataFim = Data.formatarDataCadastroParaTelaStatus(premium.get().getDataFim());

            response.put("idDigimon", premium.get().getIdDigimon());
            response.put("dataInicio", dataInicio);
            response.put("dataFim", dataFim);
        } else {
            response.put("idDigimon", idDigimon);
            response.put("dataInicio", "Sem Premium Ativo");
            response.put("dataFim", "Sem Premium Ativo");
        }

        return response;
    }

}

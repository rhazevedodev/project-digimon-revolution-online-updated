package com.example.api.service;

import com.example.api.model.entity.Premium;
import com.example.api.model.request.RequestAtivarPremium;
import com.example.api.repository.PremiumRepository;
import com.example.api.utils.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PremiumService {

    @Autowired
    PremiumRepository premiumRepository;

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

    public Map<String, Object> ativarPremium(RequestAtivarPremium request) {
        LocalDateTime agora = LocalDateTime.now();
        String statusPremium = validarPremium(request.getIdDigimon());
        Premium premium;

        if (statusPremium.equals("Ativo")) {
            Optional<Premium> premiumOptional = premiumRepository.findLatestByIdDigimon(request.getIdDigimon());

            if (premiumOptional.isPresent()) {
                premium = premiumOptional.get();
                premium.setDataFim(premium.getDataFim().plusDays(request.getPeriodoDias()));
            } else {
                premium = criarNovoPremium(request, agora);
            }
        } else {
            premium = criarNovoPremium(request, agora);
        }

        premiumRepository.save(premium);

        String dataInicio = Data.formatarDataCadastroParaTelaStatus(premium.getDataInicio());
        String dataFim = Data.formatarDataCadastroParaTelaStatus(premium.getDataFim());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("idDigimon", premium.getIdDigimon());
        response.put("dataInicio", dataInicio);
        response.put("dataFim", dataFim);

        return response;
    }

    private Premium criarNovoPremium(RequestAtivarPremium request, LocalDateTime agora) {
        Premium premium = new Premium();
        premium.setIdDigimon(request.getIdDigimon());
        premium.setTotalDias(request.getPeriodoDias());
        premium.setDataInicio(agora);
        premium.setDataFim(agora.plusDays(request.getPeriodoDias()));
        return premium;
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

}

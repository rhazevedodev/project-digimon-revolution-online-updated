package com.example.api.service.telas;

import com.example.api.enumerator.EnumDigimonRookie;
import com.example.api.model.entity.Digimon;
import com.example.api.model.entity.Missao;
import com.example.api.service.DigimonService;
import com.example.api.service.MissaoService;
import com.example.api.service.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TelaMissoesService {

    @Autowired
    private DigimonService digimonService;
    @Autowired
    private PremiumService premiumService;
    @Autowired
    private MissaoService missaoService;

    public Map<String,Object> carregarTelaMissoes(Long idDigimon){
        // Implementação do método
        Digimon digimon = digimonService.getDigimonById(idDigimon);
        String imagem =  EnumDigimonRookie.getUrlImgById(digimon.getIdRookie()).toLowerCase();

        //CARREGAR INFORMACOES PREMIUM
        String statusPremium = premiumService.validarPremium(idDigimon);
        Map<String, Object> premium = premiumService.buscarPeriodoPremium(idDigimon);

        //CARREGAR MISSAO EM ANDAMENTO
        boolean emAndamento = false;
        if(missaoService.verificarMissaoEmAndamento(idDigimon) != null){
            emAndamento = true;
        }
        //CARREGAR HORA RESGATE MISSAO
        String horaResgate = missaoService.buscarTempoResgateMissaoEmAndamento(idDigimon);

        //VALIDAR SE MISSAO PODE SER RESGATADA
        boolean resgateDisponivel = missaoService.validarResgateDisponivelMissao(idDigimon);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("url_imagem_digimon", imagem);
        response.put("nivel", digimon.getNivel());
        response.put("status_premium", statusPremium);
        response.put("data_inicio_premium", premium.get("dataInicio"));
        response.put("data_fim_premium", premium.get("dataFim"));
        response.put("emAndamento", emAndamento);
        response.put("horaResgate", horaResgate);
        response.put("resgateDisponivel", resgateDisponivel);

        return response;
    }

}

package com.example.api.service;

import com.example.api.entity.Digimon;
import com.example.api.entity.Missao;
import com.example.api.repository.MissaoRepository;
import com.example.api.utils.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MissoesService {

    private static final Logger logger = LoggerFactory.getLogger(MissoesService.class);

    private final DigimonService digimonService;
    private final JogadorService jogadorService;
    private final PremiumService premiumService;
    private final MissaoRepository missaoRepository;

    public MissoesService(DigimonService digimonService, JogadorService jogadorService, PremiumService premiumService, MissaoRepository missaoRepository) {
        this.digimonService = digimonService;
        this.jogadorService = jogadorService;
        this.premiumService = premiumService;
        this.missaoRepository = missaoRepository;
    }

    public Map<String, Object> carregarTelaMissoes(Long idDigimon){
        Map<String, Object> response = new LinkedHashMap<>();

        Digimon digimon = digimonService.getDigimonById(idDigimon);

        // Chama os métodos existentes e adiciona os resultados ao response
        response.putAll(digimonService.carregarImagemDigimon(idDigimon));
        response.putAll(premiumService.carregarInformacoesPremium(idDigimon));
       response.putAll(carregarDadosMissoes(idDigimon, response));
        response.put("nivel", digimon.getNivel());
        response.put("vida", digimon.getAtributos().getPontosVida());
        response.put("energia", digimon.getAtributos().getPontosEnergia());

        return response;
    }

    private Map<String, Object> carregarDadosMissoes(Long idDigimon, Map<String, Object> response) {
        // Implementação do método
        response.put("emAndamento", verificarMissaoEmAndamento(idDigimon) != null);
        response.put("horaResgate", buscarTempoResgateMissaoEmAndamento(idDigimon));
        response.put("resgateDisponivel", validarResgateDisponivelMissao(idDigimon));
        return response;
    }

    public Missao verificarMissaoEmAndamento(Long idDigimon) {
        Missao missao = missaoRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
        return missao;
    }

    public String buscarTempoResgateMissaoEmAndamento(Long idDigimon) {
        if(verificarMissaoEmAndamento(idDigimon) == null){
            return "Não há missão em andamento que possa ter recompensa resgatada";
        }
        Missao missao = missaoRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
        String dataFormatada = Data.formatarDataCadastroParaTelaStatus(missao.getHoraResgateDisponivel());
        return dataFormatada;
    }

    public boolean validarResgateDisponivelMissao(Long idDigimon) {
        if(verificarMissaoEmAndamento(idDigimon) == null){
            return false;
        }
        Missao missao = missaoRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
        LocalDateTime horaResgateDisponivel = missao.getHoraResgateDisponivel();
        return LocalDateTime.now().isAfter(horaResgateDisponivel);
    }

}

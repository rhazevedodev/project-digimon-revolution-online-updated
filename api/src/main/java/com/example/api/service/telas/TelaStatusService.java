package com.example.api.service.telas;

import com.example.api.enumerator.*;
import com.example.api.model.entity.Digimon;
import com.example.api.model.entity.Jogador;
import com.example.api.model.entity.RegistroConquistas;
import com.example.api.service.DigimonService;
import com.example.api.service.JogadorService;
import com.example.api.service.PremiumService;
import com.example.api.service.RegistroConquistasService;
import com.example.api.service.auxiliar.AuxDigimonService;
import com.example.api.service.auxiliar.AuxPremiumService;
import com.example.api.utils.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TelaStatusService {

    @Autowired
    private DigimonService digimonService;
    @Autowired
    private JogadorService jogadorService;
    @Autowired
    private RegistroConquistasService registroConquistasService;
    @Autowired
    private PremiumService premiumService;
    @Autowired
    private AuxPremiumService auxPremiumService;
    @Autowired
    private AuxDigimonService auxDigimonService;

    /**
     * Carrega as informações da tela de status para um Digimon específico.
     *
     * @param idDigimon o ID do Digimon
     * @return um mapa contendo as informações da tela de status
     */
    public Map<String, Object> carregarTelaStatus(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();

        Digimon digimon = digimonService.getDigimonById(idDigimon);
        if (digimon == null) {
            return response;
        }

        Long idJogador = digimonService.getIdJogadorByDigimonId(idDigimon);
        Jogador jogador = jogadorService.buscarJogadorPorId(idJogador);
        if (jogador == null) {
            return response;
        }

        String dataFormatada = Data.formatarDataCadastroParaTelaStatus(jogador.getDataCadastro());
        String tier = determinarTier(digimon);
        String indicacao = (jogador.getIndicacao() != null) ? jogador.getIndicacao() : "Sem indicação";
        String bonusBitsAtivo = digimon.isBonusBitsAtivo() ? "Ativado" : "-";
        String bonusExperienciaAtivo = digimon.isBonusExperienciaAtivo() ? "Ativado" : "-";
        int experienciaNecessaria = EnumNivelDigimon.getExperienciaNecessaria(digimon.getNivel());
        RegistroConquistas registroConquistas = registroConquistasService.getRegistroConquistas(idDigimon);
        if (registroConquistas == null) {
            registroConquistas = new RegistroConquistas();
        }

        auxPremiumService.carregarDadosPremium(idDigimon, response);
        auxDigimonService.carregarDadosDigimonGeral(idDigimon, response);

        preencherResponse(response, dataFormatada, indicacao, digimon, tier, bonusBitsAtivo, bonusExperienciaAtivo, experienciaNecessaria, registroConquistas);

        return response;
    }

    /**
     * Determina o tier de um Digimon com base no seu estágio de evolução.
     *
     * @param digimon o Digimon a ser avaliado
     * @return uma String representando o tier do Digimon
     */
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

    /**
     * Preenche o mapa de resposta com as informações detalhadas do Digimon e do jogador.
     *
     * @param response o mapa de resposta a ser preenchido
     * @param dataFormatada a data de cadastro do jogador formatada
     * @param indicacao a indicação do jogador
     * @param digimon o Digimon cujas informações serão preenchidas
     * @param tier o tier do Digimon
     * @param bonusBitsAtivo o estado do bônus de bits do Digimon
     * @param bonusExperienciaAtivo o estado do bônus de experiência do Digimon
     * @param experienciaNecessaria a experiência necessária para o próximo nível do Digimon
     * @param registroConquistas o registro de conquistas do Digimon
     */
    private void preencherResponse(Map<String, Object> response, String dataFormatada, String indicacao, Digimon digimon, String tier, String bonusBitsAtivo, String bonusExperienciaAtivo, int experienciaNecessaria, RegistroConquistas registroConquistas) {
        response.put("jogo_desde", dataFormatada);
        response.put("indicacao", indicacao);
        response.put("reserva_bits", digimon.getBits());
        response.put("reserva_diamantes", digimon.getDiamantes());
        response.put("apelido_digimon", digimon.getNome());
        response.put("tier", tier);
        response.put("digimon_estagio_1", EnumDigimonRookie.getDescricaoById(digimon.getIdRookie()));
        response.put("digimon_estagio_2", (digimon.getIdChampion() != 0) ? EnumDigimonChampion.getDescricaoById(digimon.getIdChampion()) : "-");
        response.put("digimon_estagio_3", (digimon.getIdUltimate() != 0) ? EnumDigimonUltimate.getDescricaoById(digimon.getIdUltimate()) : "-");
        response.put("digimon_estagio_4", (digimon.getIdMega() != 0) ? EnumDigimonMega.getDescricaoById(digimon.getIdMega()) : "-");
        response.put("nivel", digimon.getNivel());
        response.put("bonus_experiencia", bonusExperienciaAtivo);
        response.put("bonus_bits", bonusBitsAtivo);
        response.put("atributo1_forca", digimon.getAtributos().getPontosForca());
        response.put("atributo2_inteligencia", digimon.getAtributos().getPontosInteligencia());
        response.put("atributo3_conhecimento", digimon.getAtributos().getPontosConhecimento());
        response.put("atributo4_agilidade", digimon.getAtributos().getPontosAgilidade());
        response.put("atributo0_vida", digimon.getAtributos().getPontosVida());
        response.put("experiencia", digimon.getPontosExperiencia());
        response.put("experienciaNecessaria", experienciaNecessaria);
        response.put("bits_obtidos", registroConquistas.getBitsObtidos());
        response.put("exp_obtida", registroConquistas.getExpObtida());
        response.put("cacadas_concluidas", registroConquistas.getCacadasConcluidas());
        response.put("missoes_concluidas", registroConquistas.getMissoesConcluidas());
    }

}

package com.example.api.service.telas;

import com.example.api.enumerator.EnumDigimonInvasores;
import com.example.api.enumerator.EnumDigimonRookie;
import com.example.api.model.entity.Digimon;
import com.example.api.model.entity.Invasao;
import com.example.api.service.DigimonService;
import com.example.api.service.InvasaoService;
import com.example.api.service.PremiumService;
import com.example.api.utils.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TelaInvasoesService {

    @Autowired
    private DigimonService digimonService;
    @Autowired
    private PremiumService premiumService;
    @Autowired
    private InvasaoService invasaoService;
    public Map<String, Object> carregarTelaInvasoes(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();

        if(!invasaoService.existeInvasao()){
            invasaoService.iniciarInvasor();
        }

        // Carregar dados da invasão
        Invasao invasaoAtiva = invasaoService.existInvasionActive();
        Invasao ultimaInvasaoDerrotada = invasaoService.verificarUltimaInvasaoDerrotada();

        //Carregar informações invasão
        carregarInformacoesInvasao(invasaoAtiva, ultimaInvasaoDerrotada, response);

        // Carregar informações premium
        carregarInformacoesPremium(idDigimon, response);

        // Carregar imagem do Digimon
        carregarDadosDigimon(idDigimon, response);

        return response;
    }

    private void carregarInformacoesInvasao(Invasao invasaoAtiva, Invasao ultimaInvasaoDerrotada, Map<String, Object> response) {
        Invasao dadosInvasao = invasaoAtiva != null ? invasaoAtiva : ultimaInvasaoDerrotada;

        if (dadosInvasao != null) {
            // Verificar se o invasor foi derrotado e alterar a URL da imagem
//            String urlImg = null;
//            if (dadosInvasao.isDerrotado()) {
//                urlImg = EnumDigimonInvasores.getUrlImgById(dadosInvasao.getIdInvasor());
//                if (urlImg != null && urlImg.endsWith(".jpg")) {
//                    urlImg = urlImg.substring(0, urlImg.length() - 4) + "_derrotado.jpg";
//                }
//            }
            response.put("idInvasor", dadosInvasao.getIdInvasor());
            response.put("nomeInvasor", dadosInvasao.getNomeInvasor());
            response.put("urlImg", EnumDigimonInvasores.getUrlImgById(dadosInvasao.getIdInvasor()).toLowerCase());
            response.put("recompensaBits", EnumDigimonInvasores.getRecompensaBitsById(dadosInvasao.getIdInvasor()));
            response.put("recompensaDiamantes", EnumDigimonInvasores.getRecompensaDiamantesById(dadosInvasao.getIdInvasor()));
            response.put("energiaVital", dadosInvasao.getEnergiaVital());
            response.put("dataInvasao", dadosInvasao.getDataInvasao());
            response.put("ataquesSofridos", dadosInvasao.getAtaquesSofridos());
            response.put("derrotadoPor", dadosInvasao.getDerrotadoPor());
            response.put("derrotadoEm", dadosInvasao.getDerrotadoEm());
            response.put("derrotado", dadosInvasao.isDerrotado());
        }
        response.put("idInvasor", dadosInvasao.getIdInvasor());
        response.put("nomeInvasor", dadosInvasao.getNomeInvasor());
        response.put("urlImg", EnumDigimonInvasores.getUrlImgById(dadosInvasao.getIdInvasor()).toLowerCase());
        response.put("recompensaBits", EnumDigimonInvasores.getRecompensaBitsById(dadosInvasao.getIdInvasor()));
        response.put("recompensaDiamantes", EnumDigimonInvasores.getRecompensaDiamantesById(dadosInvasao.getIdInvasor()));
        response.put("energiaVital", dadosInvasao.getEnergiaVital());
        response.put("dataInvasao", Data.formatarDataCadastroParaTelaStatus(dadosInvasao.getDataInvasao()));
        response.put("ataquesSofridos", dadosInvasao.getAtaquesSofridos());
        response.put("derrotadoPor", dadosInvasao.getDerrotadoPor());
        response.put("derrotadoEm", dadosInvasao.getDerrotadoEm());
        response.put("derrotado", dadosInvasao.isDerrotado());
    }


    private void carregarInformacoesPremium(Long idDigimon, Map<String, Object> response) {
        String statusPremium = premiumService.validarPremium(idDigimon);
        Map<String, Object> premium = premiumService.buscarPeriodoPremium(idDigimon);

        response.put("status_premium", statusPremium);
        response.put("data_inicio_premium", premium.get("dataInicio"));
        response.put("data_fim_premium", premium.get("dataFim"));
    }

    private void carregarDadosDigimon(Long idDigimon, Map<String, Object> response) {
        Digimon digimon = digimonService.getDigimonById(idDigimon);
        String imagem = EnumDigimonRookie.getUrlImgById(digimon.getIdRookie()).toLowerCase();
        response.put("url_imagem_digimon", imagem);
        response.put("pontos_vida_digimon", digimon.getAtributos().getPontosVida());
    }


}

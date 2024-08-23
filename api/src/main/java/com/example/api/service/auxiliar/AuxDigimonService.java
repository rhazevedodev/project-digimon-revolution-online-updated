package com.example.api.service.auxiliar;

import com.example.api.enumerator.EnumDigimonRookie;
import com.example.api.model.entity.Digimon;
import com.example.api.service.DigimonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AuxDigimonService {

    @Autowired
    private DigimonService digimonService;

    /**
     * Método responsável por carregar os dados gerais do Digimon nas telas
     * @param idDigimon ID do Digimon
     * @return Map contendo os dados gerais do Digimon
     */
    public Map<String, Object> carregarDadosDigimonGeral(Long idDigimon, Map<String, Object> response) {

        // Carregar informações do Digimon
        Digimon digimon = digimonService.getDigimonById(idDigimon);
        if (digimon == null) {
            response.put("url_imagem_digimon", "imagem_nao_disponivel");
            response.put("pontos_vida_digimon", 0);
            return response;
        }

        String imagem = EnumDigimonRookie.getUrlImgById(digimon.getIdRookie()).toLowerCase();
        response.put("url_imagem_digimon", imagem);
        response.put("pontos_vida_digimon", digimon.getAtributos().getPontosVida());
        return response;
    }

}

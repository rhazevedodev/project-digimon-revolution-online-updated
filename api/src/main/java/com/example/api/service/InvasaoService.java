package com.example.api.service;

import com.example.api.enumerator.EnumDigimonInvasores;
import com.example.api.model.entity.Invasao;
import com.example.api.repository.InvasaoRepository;
import com.example.api.utils.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InvasaoService {

    @Autowired
    private InvasaoRepository invasaoRepository;

    public boolean existeInvasao(){
        return invasaoRepository.existsAnyInvasion();
    }

    public Invasao existInvasionActive() {
        return invasaoRepository.findFirstByDerrotado(false);
    }

    public Invasao verificarUltimaInvasaoDerrotada() {
        return invasaoRepository.findFirstByDerrotado(true);
    }

    public void iniciarInvasor() {
        int idInvasor = Randomizer.gerarNumeroMinMax(1,5);

        Invasao novaInvasao = new Invasao();

        novaInvasao.setIdInvasor(idInvasor);

        String nomeInvasor = EnumDigimonInvasores.getNomeById(idInvasor);
        novaInvasao.setNomeInvasor(nomeInvasor);

        int energiaVital = EnumDigimonInvasores.getEnergiaVitalById(idInvasor);
        novaInvasao.setEnergiaVital(energiaVital);

        novaInvasao.setDataInvasao(LocalDateTime.now());
        novaInvasao.setAtaquesSofridos(0);
        novaInvasao.setDerrotadoPor(null);
        novaInvasao.setDerrotadoEm(null);
        novaInvasao.setDerrotado(false);

        invasaoRepository.save(novaInvasao);
    }


}

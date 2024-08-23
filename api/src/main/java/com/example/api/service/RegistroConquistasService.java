package com.example.api.service;

import com.example.api.model.entity.Digimon;
import com.example.api.model.entity.RegistroConquistas;
import com.example.api.repository.RegistroConquistasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistroConquistasService {

    @Autowired
    RegistroConquistasRepository registroConquistasRepository;

    public RegistroConquistas getRegistroConquistas(Long idDigimon) {
        return registroConquistasRepository.findByIdDigimon(idDigimon);
    }

    @Transactional
    public RegistroConquistas salvarRegistroSubirNivel(Digimon digimon){
        // Atualiza o registro para conquistas
        RegistroConquistas registro = registroConquistasRepository.findByIdDigimon(digimon.getId());
        if (registro != null) {
            registro.setNiveisTotais(registro.getNiveisTotais() + 1);
        } else {
            registro = new RegistroConquistas();
            registro.setIdJogador(digimon.getIdJogador());
            registro.setIdDigimon(digimon.getId());
            registro.setNiveisTotais(1);
        }
        registro.setUltimaAlteracao(LocalDateTime.now());

        // Salva o registro de conquistas (atualizado ou novo)
        registroConquistasRepository.save(registro);

        return registro;
    }

    @Transactional
    public RegistroConquistas salvarRegistroCacadaMissao(Digimon digimon, int recompensaBits, int quantidadeFragmentosGanhos, String tipoRegistro){
        // Atualiza o registro para conquistas
        RegistroConquistas registro = registroConquistasRepository.findByIdDigimon(digimon.getId());
        if (registro != null) {
            if(tipoRegistro.equals("CACADA")) {
                registro.setCacadasConcluidas(registro.getCacadasConcluidas() + 1);
            } else {
                registro.setMissoesConcluidas(registro.getMissoesConcluidas() + 1);
            }
            registro.setBitsObtidos(registro.getBitsObtidos() + recompensaBits);
            registro.setFragmentosObtidos(registro.getFragmentosObtidos() + quantidadeFragmentosGanhos);
        } else {
            registro = new RegistroConquistas();
            registro.setIdJogador(digimon.getIdJogador());
            registro.setIdDigimon(digimon.getId());
            if(tipoRegistro.equals("CACADA")) {
                registro.setCacadasConcluidas(1);
            } else {
                registro.setMissoesConcluidas(1);
            }
            registro.setBitsObtidos(recompensaBits);
            registro.setFragmentosObtidos(quantidadeFragmentosGanhos);
        }
        registro.setUltimaAlteracao(LocalDateTime.now());

        // Salva o registro de conquistas (atualizado ou novo)
        registroConquistasRepository.save(registro);

        return registro;
    }
}

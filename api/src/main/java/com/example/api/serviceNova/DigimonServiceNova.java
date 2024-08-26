package com.example.api.serviceNova;

import com.example.api.model.entity.Digimon;
import com.example.api.repository.DigimonRepository;
import com.example.api.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigimonServiceNova {

    @Autowired
    private DigimonRepository digimonRepository;

    @Autowired
    JogadorService jogadorService;

    public boolean getDigimonByIdJogador(int idJogador) {
        return digimonRepository.existsByIdJogador(idJogador);

    }

    public List<Digimon> getDigimonByUsuario(String nomeUsuario) {
        return digimonRepository.getDigimonByIdJogador(jogadorService.getIdByUsuario(nomeUsuario));
    }
}

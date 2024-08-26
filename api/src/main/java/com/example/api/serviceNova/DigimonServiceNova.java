package com.example.api.serviceNova;

import com.example.api.repository.DigimonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DigimonServiceNova {

    @Autowired
    private DigimonRepository digimonRepository;

    public boolean getDigimonByIdJogador(int idJogador) {
        return digimonRepository.existsByIdJogador(idJogador);

    }
}

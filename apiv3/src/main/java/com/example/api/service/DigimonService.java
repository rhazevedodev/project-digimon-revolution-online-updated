package com.example.api.service;

import com.example.api.repository.DigimonRepository;
import org.springframework.stereotype.Service;

@Service
public class DigimonService {

    private DigimonRepository digimonRepository;

    public DigimonService(DigimonRepository digimonRepository) {
        this.digimonRepository = digimonRepository;
    }

    public boolean getDigimonByIdJogador(int idJogador) {
        return digimonRepository.existsByIdJogador(idJogador);

    }
}

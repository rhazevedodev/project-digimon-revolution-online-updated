package com.example.api.domain.jogador;

import org.springframework.stereotype.Service;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    public Jogador cadastrarJogador(Jogador jogador) {
        // Lógica de validação e regras de negócio
        return jogadorRepository.save(jogador);
    }

}

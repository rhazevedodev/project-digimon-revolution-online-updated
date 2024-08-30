package com.example.api.domain.jogador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JogadorService {

    private static final Logger logger = LoggerFactory.getLogger(JogadorService.class);

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    public Jogador cadastrarJogador(Jogador jogador) {
        // Lógica de validação e regras de negócio
        logger.info("Cadastrando jogador com validações de negócio: {}", jogador);
        if(emailEmUso(jogador.getEmail())) {
            logger.warn("Tentativa de cadastro com email já em uso: {}", jogador.getEmail());
            throw new RuntimeException("Email já está em uso");
        }
        return jogadorRepository.save(jogador);
    }

    private boolean emailEmUso(String email) {
        // Lógica para verificar se o email já está em uso
        return false;
    }

}

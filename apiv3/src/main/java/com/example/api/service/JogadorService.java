package com.example.api.service;

import com.example.api.entity.Jogador;
import com.example.api.repository.JogadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JogadorService {

    private static final Logger logger = LoggerFactory.getLogger(JogadorService.class);

    private final JogadorRepository jogadorRepository;
    private final CriptografiaService criptografiaService;

    public JogadorService(JogadorRepository jogadorRepository, CriptografiaService criptografiaService) {
        this.jogadorRepository = jogadorRepository;
        this.criptografiaService = criptografiaService;
    }

    public Jogador cadastrarJogador(Jogador jogador) {
        logger.info("Cadastrando jogador com validações de negócio: nome={}, email={}", jogador.getUsuario(), jogador.getEmail());
        if (emailEmUso(jogador.getEmail())) {
            logger.warn("Tentativa de cadastro com email já em uso: {}", jogador.getEmail());
            throw new RuntimeException("Email já está em uso");
        }
        logger.info("Criptografando a senha do jogador antes de salvar");
        String senhaCriptografada = null;
        try {
            senhaCriptografada = criptografiaService.encrypt3DES(jogador.getSenha());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        jogador.setSenha(senhaCriptografada);
        return jogadorRepository.save(jogador);
    }


    private boolean emailEmUso(String email) {
        return jogadorRepository.existsByEmail(email);
    }

}



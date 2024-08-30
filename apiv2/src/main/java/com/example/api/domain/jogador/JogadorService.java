package com.example.api.domain.jogador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JogadorService {

    private static final Logger logger = LoggerFactory.getLogger(JogadorService.class);

    private final JogadorRepository jogadorRepository;
    private final PasswordEncoder passwordEncoder;

    public JogadorService(JogadorRepository jogadorRepository, PasswordEncoder passwordEncoder) {
        this.jogadorRepository = jogadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Jogador cadastrarJogador(Jogador jogador) {
        // Lógica de validação e regras de negócio
        logger.info("Cadastrando jogador com validações de negócio: {}", jogador);
        if(emailEmUso(jogador.getEmail())) {
            logger.warn("Tentativa de cadastro com email já em uso: {}", jogador.getEmail());
            throw new RuntimeException("Email já está em uso");
        }
        logger.info("Criptografando a senha do jogador antes de salvar", jogador);
        String senhaCriptografada = passwordEncoder.encode(jogador.getSenha());
        jogador.setSenha(senhaCriptografada);
        return jogadorRepository.save(jogador);
    }

    private boolean emailEmUso(String email) {
        // Lógica para verificar se o email já está em uso
        return jogadorRepository.existsByEmail(email);
    }

}

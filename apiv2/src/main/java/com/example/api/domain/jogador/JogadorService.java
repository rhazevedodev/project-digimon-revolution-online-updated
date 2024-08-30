package com.example.api.domain.jogador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço responsável pelas operações relacionadas à entidade Jogador.
 */
@Service
public class JogadorService {

    private static final Logger logger = LoggerFactory.getLogger(JogadorService.class);

    private final JogadorRepository jogadorRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Construtor da classe JogadorService.
     *
     * @param jogadorRepository O repositório de jogadores.
     * @param passwordEncoder   O codificador de senhas.
     */
    public JogadorService(JogadorRepository jogadorRepository, PasswordEncoder passwordEncoder) {
        this.jogadorRepository = jogadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Cadastra um novo jogador após realizar validações de negócio.
     *
     * @param jogador O jogador a ser cadastrado.
     * @return O jogador cadastrado.
     * @throws RuntimeException Se o email do jogador já estiver em uso.
     */
    @Transactional
    public Jogador cadastrarJogador(Jogador jogador) {
        logger.info("Cadastrando jogador com validações de negócio: nome={}, email={}", jogador.getNome(), jogador.getEmail());
        if (emailEmUso(jogador.getEmail())) {
            logger.warn("Tentativa de cadastro com email já em uso: {}", jogador.getEmail());
            throw new RuntimeException("Email já está em uso");
        }
        logger.info("Criptografando a senha do jogador antes de salvar");
        String senhaCriptografada = passwordEncoder.encode(jogador.getSenha());
        jogador.setSenha(senhaCriptografada);
        return jogadorRepository.save(jogador);
    }

    /**
     * Verifica se a senha fornecida corresponde à senha codificada.
     *
     * @param senhaPlain      A senha em texto plano.
     * @param senhaCodificada A senha codificada.
     * @return true se as senhas corresponderem, false caso contrário.
     */
    public boolean verificarSenha(String senhaPlain, String senhaCodificada) {
        return passwordEncoder.matches(senhaPlain, senhaCodificada);
    }

    /**
     * Verifica se um email já está em uso no repositório.
     *
     * @param email O email a ser verificado.
     * @return true se o email já estiver em uso, false caso contrário.
     */
    private boolean emailEmUso(String email) {
        return jogadorRepository.existsByEmail(email);
    }
}
package com.example.api.domain.jogador;

import com.example.api.infra.helper.Criptografia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço responsável pelas operações relacionadas à entidade Jogador.
 */
@Service
public class JogadorService {

    private static final Logger logger = LoggerFactory.getLogger(JogadorService.class);

    private final JogadorRepository jogadorRepository;

    /**
     * Construtor da classe JogadorService.
     *
     * @param jogadorRepository O repositório de jogadores.
     */
    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
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
        String senhaCriptografada = null;
        try {
            senhaCriptografada = Criptografia.encrypt3DES(jogador.getSenha());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        jogador.setSenha(senhaCriptografada);
        return jogadorRepository.save(jogador);
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
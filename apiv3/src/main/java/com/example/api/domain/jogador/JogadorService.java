package com.example.api.domain.jogador;

import com.example.api.app.dto.RequestAutenticarJogador;
import com.example.api.domain.token.Token;
import com.example.api.domain.token.TokenRepository;
import com.example.api.infra.helper.Criptografia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

/**
 * Serviço responsável pelas operações relacionadas à entidade Jogador.
 */
@Service
public class JogadorService {

    private static final Logger logger = LoggerFactory.getLogger(JogadorService.class);

    private final JogadorRepository jogadorRepository;

    private final TokenRepository tokenRepository;

    private static final long TOKEN_VALIDITY_MINUTES = 60;

    /**
     * Construtor da classe JogadorService.
     *
     * @param jogadorRepository O repositório de jogadores.
     */
    public JogadorService(JogadorRepository jogadorRepository, TokenRepository tokenRepository) {
        this.jogadorRepository = jogadorRepository;
        this.tokenRepository = tokenRepository;
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
        logger.info("Cadastrando jogador com validações de negócio: nome={}, email={}", jogador.getUsuario(), jogador.getEmail());
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

    public Optional<String> autenticarJogador(RequestAutenticarJogador request) {
        logger.info("Autenticando jogador: email={}", request);
        Optional<Jogador> jogadorOptional = jogadorRepository.findByUsuario(request.getUsuario());
        if (jogadorOptional.isPresent()) {
            Jogador jogador = jogadorOptional.get();
            if (jogador.getSenha().equals(request.getSenha())) {
                Optional<Token> tokenOptional = tokenRepository.findByUsername(request.getUsuario());
                if (tokenOptional.isPresent()) {
                    Token token = tokenOptional.get();
                    if (token.getExpirationTime().isAfter(LocalDateTime.now())) {
                        return Optional.of(token.getToken());
                    } else {
                        tokenRepository.delete(token);
                    }
                }
                String newToken = generateToken();
                Token token = new Token();
                token.setToken(newToken);
                token.setUsername(request.getUsuario());
                token.setExpirationTime(LocalDateTime.now().plusMinutes(TOKEN_VALIDITY_MINUTES));
                tokenRepository.save(token);
                return Optional.of(newToken);
            }
        }
        return Optional.empty();

    }

    private String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[24];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
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
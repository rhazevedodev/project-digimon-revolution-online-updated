package com.example.api.infra.persistence.token;

import com.example.api.domain.token.Token;
import com.example.api.infra.persistence.jogador.JogadorRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(JogadorRepositoryImpl.class);

    private final JpaTokenRepository jpaTokenRepository;

    public TokenRepositoryImpl(JpaTokenRepository jpaTokenRepository) {
        this.jpaTokenRepository = jpaTokenRepository;
    }

    public void save(Token token) {
        logger.info("Salvando token: {}", token);
        jpaTokenRepository.save(token);
    }
}

package com.example.api.infra.jogador;

import com.example.api.domain.jogador.Jogador;
import com.example.api.domain.jogador.JogadorRepository;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class JogadorRepositoryImpl implements JogadorRepository {

    private static final Logger logger = LoggerFactory.getLogger(JogadorRepositoryImpl.class);

    private final JpaJogadorRepository jpaJogadorRepository;

    public JogadorRepositoryImpl(JpaJogadorRepository jpaJogadorRepository) {
        this.jpaJogadorRepository = jpaJogadorRepository;
    }

    @Override
    public Jogador save(Jogador jogador) {
        // Lógica para salvar no banco de dados
        logger.info("Salvando jogador: {}", jogador);
        return jpaJogadorRepository.save(jogador);
    }
}

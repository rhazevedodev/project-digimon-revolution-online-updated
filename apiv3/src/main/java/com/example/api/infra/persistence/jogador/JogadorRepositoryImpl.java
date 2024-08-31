package com.example.api.infra.persistence.jogador;

import com.example.api.domain.jogador.Jogador;
import com.example.api.domain.jogador.JogadorRepository;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Implementação do repositório de jogadores utilizando JPA.
 */
@Repository
public class JogadorRepositoryImpl implements JogadorRepository {

    private static final Logger logger = LoggerFactory.getLogger(JogadorRepositoryImpl.class);

    private final JpaJogadorRepository jpaJogadorRepository;

    /**
     * Construtor da classe JogadorRepositoryImpl.
     *
     * @param jpaJogadorRepository Repositório JPA de jogadores.
     */
    public JogadorRepositoryImpl(JpaJogadorRepository jpaJogadorRepository) {
        this.jpaJogadorRepository = jpaJogadorRepository;
    }

    /**
     * Salva um jogador no banco de dados.
     *
     * @param jogador O jogador a ser salvo.
     * @return O jogador salvo.
     */
    @Override
    public Jogador save(Jogador jogador) {
        logger.info("Salvando jogador: {}", jogador);
        return jpaJogadorRepository.save(jogador);
    }

    /**
     * Verifica se um jogador com o email especificado já existe no banco de dados.
     *
     * @param email O email a ser verificado.
     * @return true se um jogador com o email especificado já existir, false caso contrário.
     */
    @Override
    public boolean existsByEmail(String email) {
        return jpaJogadorRepository.existsByEmail(email);
    }

    @Override
    public Optional<Jogador> findByUsuario(String usuario) {
        return jpaJogadorRepository.findByUsuario(usuario);
    }
}
package com.example.api.domain.jogador;

import java.util.Optional;

/**
 * Repositório para a entidade Jogador.
 * Fornece métodos para salvar e verificar a existência de jogadores por email.
 */
public interface JogadorRepository {

    /**
     * Salva um jogador no repositório.
     *
     * @param jogador O jogador a ser salvo.
     * @return O jogador salvo.
     */
    Jogador save(Jogador jogador);

    /**
     * Verifica se um jogador com o email especificado já existe no repositório.
     *
     * @param email O email a ser verificado.
     * @return true se um jogador com o email especificado já existir, false caso contrário.
     */
    boolean existsByEmail(String email);

    Optional<Jogador> findByUsuario(String usuario);
}
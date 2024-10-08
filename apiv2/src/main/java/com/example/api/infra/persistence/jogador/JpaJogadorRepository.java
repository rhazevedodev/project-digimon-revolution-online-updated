package com.example.api.infra.persistence.jogador;

import com.example.api.domain.jogador.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório JPA para a entidade Jogador.
 * Fornece métodos para realizar operações de persistência no banco de dados.
 */
@Repository
public interface JpaJogadorRepository extends JpaRepository<Jogador, Long> {

    /**
     * Verifica se um jogador com o email especificado já existe no banco de dados.
     *
     * @param email O email a ser verificado.
     * @return true se um jogador com o email especificado já existir, false caso contrário.
     */
    boolean existsByEmail(String email);

    Optional<Jogador> findByUsuario(String usuario);
}
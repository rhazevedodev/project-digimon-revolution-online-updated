package com.example.api.infra.jogador;

import com.example.api.domain.jogador.Jogador;
import com.example.api.domain.jogador.JogadorRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JogadorRepositoryImpl implements JogadorRepository {

    private final JpaJogadorRepository jpaJogadorRepository;

    public JogadorRepositoryImpl(JpaJogadorRepository jpaJogadorRepository) {
        this.jpaJogadorRepository = jpaJogadorRepository;
    }

    @Override
    public Jogador save(Jogador jogador) {
        // Lógica para salvar no banco de dados
        return jpaJogadorRepository.save(jogador);
    }
}

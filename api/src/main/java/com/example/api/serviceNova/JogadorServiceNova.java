package com.example.api.serviceNova;

import com.example.api.model.entity.Jogador;
import com.example.api.repository.JogadorRepository;
import com.example.api.utils.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogadorServiceNova {

    @Autowired
    private JogadorRepository jogadorRepository;

    public Jogador cadastrarJogador(Jogador jogador) throws Exception {
        if (jogadorRepository.existsByUsuario(jogador.getUsuario())) {
            throw new RuntimeException("Usuário já cadastrado");
        }
        if (jogadorRepository.existsByEmail(jogador.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        if(!Data.validarDataNascimento(jogador.getDataNascimento())) {
            throw new RuntimeException("Data de nascimento inválida");
        }
        return jogadorRepository.save(jogador);
    }

}

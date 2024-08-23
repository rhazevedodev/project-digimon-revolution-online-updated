package com.example.api.service;

import com.example.api.model.entity.Jogador;
import com.example.api.repository.JogadorRepository;
import com.example.api.utils.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;
    @Autowired
    LogService logService;

    public Jogador cadastrarJogador(Jogador jogador) throws Exception {
        if (jogadorRepository.existsByUsuario(jogador.getUsuario())) {
            logService.logAction("Cadastro Jogador - Erro", "Usuário já cadastrado");
            throw new RuntimeException("Usuário já cadastrado");
        }
        if (jogadorRepository.existsByEmail(jogador.getEmail())) {
            logService.logAction("Cadastro Jogador - Erro", "Email já cadastrado");
            throw new RuntimeException("Email já cadastrado");
        }
        if(!Data.validarDataNascimento(jogador.getDataNascimento())) {
            logService.logAction("Cadastro Jogador - Erro", "Data de nascimento inválida");
            throw new RuntimeException("Data de nascimento inválida");
        }
        logService.logAction("Cadastro Jogador", "Jogador " + jogador.getUsuario() + " cadastrado");
        return jogadorRepository.save(jogador);
    }

    public Boolean verificarJogadorExistente(Long idJogador){
        return jogadorRepository.existsById(idJogador);
    }

    public String getNomeJogador(Long idJogador) {
        return jogadorRepository.findById(idJogador).get().getUsuario();
    }

    public int getIdByUsuario(String usuario) {
        Optional<Jogador> jogador = jogadorRepository.findByUsuario(usuario);
        if(jogador.isPresent()) {
            return jogador.get().getId().intValue();
        }
        return -1;
    }

    public Jogador buscarJogadorPorId(Long idJogador) {
        Optional<Jogador> jogador = jogadorRepository.findById(idJogador);
        if(jogador.isPresent()) {
            return jogador.get();
        }
        return null;
    }

}

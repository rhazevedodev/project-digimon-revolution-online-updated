package com.example.api.app.jogador;

import com.example.api.domain.jogador.Jogador;
import com.example.api.domain.jogador.JogadorService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CadastrarJogadorUseCase {

    private final JogadorService jogadorService;

    public CadastrarJogadorUseCase(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }
    public Jogador executar(Jogador jogador){
        // Pode ter lógica adicional aqui, como conversões de DTO, etc.
        return jogadorService.cadastrarJogador(jogador);
    }


}

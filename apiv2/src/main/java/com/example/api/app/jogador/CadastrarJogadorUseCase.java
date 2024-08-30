package com.example.api.app.jogador;

import com.example.api.domain.jogador.Jogador;
import com.example.api.domain.jogador.JogadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CadastrarJogadorUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CadastrarJogadorUseCase.class);

    private final JogadorService jogadorService;

    public CadastrarJogadorUseCase(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    public Jogador executar(Jogador jogador) {
        // Pode ter lógica adicional aqui, como conversões de DTO, etc.
        logger.info("Executando caso de uso para cadastrar jogador: {}", jogador);
        Jogador novoJogador = jogadorService.cadastrarJogador(jogador);
        logger.info("Jogador cadastrado com sucesso: {}", novoJogador);
        return novoJogador;
    }


}

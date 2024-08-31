package com.example.api.app.jogador;

import com.example.api.app.dto.RequestAutenticarJogador;
import com.example.api.domain.jogador.JogadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutenticarJogadorUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CadastrarJogadorUseCase.class);

    private final JogadorService jogadorService;

    public AutenticarJogadorUseCase(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    public Optional<String> autenticar(RequestAutenticarJogador request) {
        logger.info("Executando caso de uso para autenticar jogador: {}", request);
        return jogadorService.autenticarJogador(request);
    }
}

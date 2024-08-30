package com.example.api.infra.web;

import com.example.api.app.jogador.CadastrarJogadorUseCase;
import com.example.api.domain.jogador.Jogador;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    private static final Logger logger = LoggerFactory.getLogger(JogadorController.class);

    private final CadastrarJogadorUseCase cadastrarJogadorUseCase;

    public JogadorController(CadastrarJogadorUseCase cadastrarJogadorUseCase) {
        this.cadastrarJogadorUseCase = cadastrarJogadorUseCase;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarJogador(@Valid @RequestBody Jogador jogador, HttpServletRequest request){
        logger.info("Requisição para cadastrar jogador recebida: {}", jogador);
        try {
            // Tratamento de IP ou outras informações do request podem ser feitas aqui ou passadas para o caso de uso.
            Jogador novoJogador = cadastrarJogadorUseCase.executar(jogador);
            logger.info("Jogador cadastrado com sucesso: {}", novoJogador);
            return new ResponseEntity<>(novoJogador, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar jogador: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

package com.example.api.infra.web;

import com.example.api.app.dto.RequestAutenticarJogador;
import com.example.api.app.jogador.AutenticarJogadorUseCase;
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

import java.util.Optional;

/**
 * Controlador REST para operações relacionadas a jogadores.
 */
@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    private static final Logger logger = LoggerFactory.getLogger(JogadorController.class);

    private final CadastrarJogadorUseCase cadastrarJogadorUseCase;
    private final AutenticarJogadorUseCase autenticarJogadorUseCase;

    /**
     * Construtor da classe JogadorController.
     *
     * @param cadastrarJogadorUseCase Caso de uso para cadastrar jogadores.
     */
    public JogadorController(CadastrarJogadorUseCase cadastrarJogadorUseCase, AutenticarJogadorUseCase autenticarJogadorUseCase) {
        this.cadastrarJogadorUseCase = cadastrarJogadorUseCase;
        this.autenticarJogadorUseCase = autenticarJogadorUseCase;
    }

    /**
     * Endpoint para cadastrar um novo jogador.
     *
     * @param jogador Objeto Jogador contendo os dados do jogador a ser cadastrado.
     * @param request Objeto HttpServletRequest contendo informações da requisição HTTP.
     * @return Um ResponseEntity contendo o jogador cadastrado e o status HTTP CREATED, ou uma mensagem de erro e o status HTTP BAD\_REQUEST.
     */
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarJogador(@Valid @RequestBody Jogador jogador, HttpServletRequest request){
        logger.info("Requisição para cadastrar jogador recebida: {}", jogador);
        try {
            // Tratamento de IP ou outras informações do request podem ser feitas aqui ou passadas para o caso de uso.
            Jogador novoJogador = cadastrarJogadorUseCase.cadastrar(jogador);
            logger.info("Jogador cadastrado com sucesso: {}", novoJogador);
            return new ResponseEntity<>(novoJogador, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar jogador: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticarJogador(@Valid @RequestBody RequestAutenticarJogador jogador, HttpServletRequest request){
        logger.info("Requisição para autenticar jogador recebida: {}", jogador);
        try {
            // Tratamento de IP ou outras informações do request podem ser feitas aqui ou passadas para o caso de uso.
            Optional<String> novoJogador = autenticarJogadorUseCase.autenticar(jogador);
            logger.info("Jogador autenticado com sucesso: {}", novoJogador);
            return new ResponseEntity<>(novoJogador, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao autenticar jogador: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
package com.example.api.controller;

import com.example.api.service.JogadorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import com.example.api.entity.Jogador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jogador")
public class JogadorController {

    private static final Logger logger = LoggerFactory.getLogger(JogadorController.class);

    private final JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "Endpoint Jogador  em funcionamento";
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarJogador(@Valid @RequestBody Jogador jogador, HttpServletRequest request) {
        logger.info("Requisição para cadastrar jogador recebida: {}", jogador);
        try {
            Jogador novoJogador = jogadorService.cadastrarJogador(jogador);
            logger.info("Jogador cadastrado com sucesso: {}", novoJogador);
            return new ResponseEntity<>(novoJogador, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar jogador: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

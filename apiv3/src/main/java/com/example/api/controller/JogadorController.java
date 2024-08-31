package com.example.api.controller;

import com.example.api.entity.dto.RequestAutenticarJogador;
import com.example.api.service.JogadorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import com.example.api.entity.Jogador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

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

    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticar(@Valid @RequestBody RequestAutenticarJogador request) {
        logger.info("Autenticando usuário: {}", request.getUsuario());
        if (request.getUsuario() == null || request.getSenha() == null) {
            logger.warn("Usuário ou senha não fornecidos");
            return new ResponseEntity<>(new RuntimeException("Usuário e senha são obrigatórios"), HttpStatus.BAD_REQUEST);
        }
        Optional<String> tokenOpt = jogadorService.autenticar(request);
        if (tokenOpt.isPresent()) {
            logger.info("Usuário autenticado com sucesso: {}", request.getUsuario());
            return ResponseEntity.ok(Map.of("token", tokenOpt.get()));
        } else {
            logger.warn("Credenciais inválidas para o usuário: {}", request.getUsuario());
            return new ResponseEntity<>(new RuntimeException("Credenciais inválidas"), HttpStatus.UNAUTHORIZED);
        }
    }

}

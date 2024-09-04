package com.example.api.controller;

import com.example.api.entity.Missao;
import com.example.api.entity.dto.RequestCarregarTelaMissoes;
import com.example.api.service.MissoesService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/telaMissoes")
public class MissoesController {

    private static final Logger logger = LoggerFactory.getLogger(MissoesController.class);

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @PostMapping("/carregar")
    public Map<String,Object> carregaTelaMissoes(@Valid @RequestBody RequestCarregarTelaMissoes request) {
        return missoesService.carregarTelaMissoes(request.getIdDigimon());
    }

    @PostMapping("/iniciarMissao")
    public ResponseEntity<?> iniciarMissao(@Valid @RequestBody Missao missao) {
        try {
            return ResponseEntity.ok(missoesService.iniciarMissao(missao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("mensagem", e.getMessage()));
        }
    }

    @PostMapping("/pegarRecompensa")
    public ResponseEntity<?> pegarRecompensa(@Valid @RequestBody Long idDigimon) {
        try {
            return ResponseEntity.ok(missoesService.resgatarRecompensaMissao(idDigimon));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

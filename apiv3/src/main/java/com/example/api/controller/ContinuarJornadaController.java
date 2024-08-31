package com.example.api.controller;

import com.example.api.service.DigimonService;
import com.example.api.service.JogadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/continuarJornada")
public class ContinuarJornadaController {

    private static final Logger logger = LoggerFactory.getLogger(ContinuarJornadaController.class);

    private final DigimonService digimonService;
    private final JogadorService jogadorService;

    public ContinuarJornadaController(DigimonService digimonService, JogadorService jogadorService) {
        this.digimonService = digimonService;
        this.jogadorService = jogadorService;
    }

    @GetMapping("/obterDigimons/{nomeUsuario}")
    public ResponseEntity<?> getDigimonByUsuario(@PathVariable String nomeUsuario) {
        logger.info("Iniciando obtenção de Digimons para o usuário: {}", nomeUsuario);
        try {
            String decryptedUsuario = jogadorService.decryptUsuario(nomeUsuario);
            logger.info("Usuário decriptado: {}", decryptedUsuario);
            return ResponseEntity.ok(digimonService.getDigimonByUsuario(decryptedUsuario));
        } catch (Exception e) {
            logger.error("Erro ao obter Digimons para o usuário: {}", nomeUsuario, e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

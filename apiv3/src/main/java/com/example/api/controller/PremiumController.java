package com.example.api.controller;

import com.example.api.service.PremiumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/premium")
public class PremiumController {

    private static final Logger logger = LoggerFactory.getLogger(PremiumController.class);

    private final PremiumService premiumService;

    public PremiumController(PremiumService premiumService) {
        this.premiumService = premiumService;
    }
    @GetMapping("/carregarInformacoesDeTelaPremium/{idDigimonUsuario}")
    public ResponseEntity<?> carregarInformacoesDeTelaPremium(@PathVariable int idDigimonUsuario) {
        logger.info("Requisição para carregar informações de tela premium recebida: {}", idDigimonUsuario);
        Map<String, Object> response = premiumService.carregarInformacoesPremium(Long.valueOf(idDigimonUsuario));
        logger.info("Informações de tela premium carregadas: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

package com.example.api.controller;

import com.example.api.entity.dto.RequestCarregarTelaStatus;
import com.example.api.service.StatusService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/telaStatus")
public class StatusController {

    private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping("/carregar")
    public ResponseEntity<?> carregarTelaStatus(@Valid @RequestBody RequestCarregarTelaStatus request) {
        logger.info("Requisição para carregar tela status recebida: {}", request);
        Map<String, Object> response = statusService.carregarTelaStatus(request.getIdDigimon());
        logger.info("Informações da tela status carregadas: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

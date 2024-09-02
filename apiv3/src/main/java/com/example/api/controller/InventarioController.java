package com.example.api.controller;

import com.example.api.entity.dto.RequestCarregarTelaInventario;
import com.example.api.service.InventarioService;
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
@RequestMapping("/api/telaInventario")
public class InventarioController {

    private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @PostMapping("/carregar")
    public ResponseEntity<?> carregarTelaInventario(@Valid @RequestBody RequestCarregarTelaInventario request) {
        logger.info("Requisição para carregar tela inventario recebida: {}", request);
        Map<String, Object> response = inventarioService.carregarTelaInventario(request.getIdDigimon());
        logger.info("Informações da tela inventario carregadas: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/carregarInventario")
    public ResponseEntity<?> carregarInventario(@Valid @RequestBody RequestCarregarTelaInventario request) {
        logger.info("Requisição para carregar inventario recebida: {}", request);
        Map<String, Object> response = inventarioService.carregarInventario(request.getIdDigimon());
        logger.info("Informações do inventario carregadas: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

package com.example.api.controller;

import com.example.api.entity.dto.RequestCarregarTelaMissoes;
import com.example.api.service.MissoesService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

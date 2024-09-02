package com.example.api.controller;

import com.example.api.entity.dto.RequestCarregarTelaAtributos;
import com.example.api.entity.dto.RequestSalvarAtributos;
import com.example.api.service.AtributosService;
import com.example.api.service.StatusService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/telaAtributos")
public class AtributosController {

    private static final Logger logger = LoggerFactory.getLogger(AtributosController.class);

    private final AtributosService atributosService;

    public AtributosController(AtributosService atributosService) {
        this.atributosService = atributosService;
    }

    @PostMapping("/carregar")
    public Map<String,Object> carregaTelaAtributos(@Valid @RequestBody RequestCarregarTelaAtributos request) {
        return atributosService.carregarTelaAtributos(request.getIdDigimon());
    }

    @PostMapping("salvarAtributos")
    public Map<String, Object> salvarAtributos(@Valid @RequestBody RequestSalvarAtributos request) {
        return atributosService.salvarAtributos(request);
    }
}

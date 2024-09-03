package com.example.api.controller;

import com.example.api.entity.dto.RequestCarregarTelaInventario;
import com.example.api.entity.dto.RequestRecuperarEnergiaVida;
import com.example.api.service.DigimonService;
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
@RequestMapping("/api/digimon")
public class DigimonController {

    private static final Logger logger = LoggerFactory.getLogger(ContinuarJornadaController.class);

    private final DigimonService digimonService;

    public DigimonController(DigimonService digimonService) {
        this.digimonService = digimonService;
    }

    @PostMapping("/recuperarVidaEnergia")
    public ResponseEntity<?> carregarTelaInventario(@Valid @RequestBody RequestRecuperarEnergiaVida request) {
//        digimonService.recuperarEnergiaVida(request.getIdDigimon());
//        System.out.println("energia e vida foram restaurados");
//        return new ResponseEntity<>("energia e vida foram restaurados", HttpStatus.OK);
        return null;
    }
}

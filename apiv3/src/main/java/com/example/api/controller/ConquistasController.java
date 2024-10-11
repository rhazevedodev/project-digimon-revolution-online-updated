package com.example.api.controller;

import com.example.api.service.ConquistasService;
import com.example.api.service.DigimonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/telaConquistas")
public class ConquistasController {

    @Autowired
    private ConquistasService conquistasService;
    @Autowired
    private DigimonService digimonService;

    @GetMapping("/carregar/{idDigimon}")
    public ResponseEntity<?> carregarTelaConquistas(@PathVariable String idDigimon) {;
        Long idJogador = digimonService.getIdJogadorByDigimonId(Long.valueOf(idDigimon));
        return new ResponseEntity<>(conquistasService.listarConquistasDoJogador(idJogador), HttpStatus.OK);
    }

}

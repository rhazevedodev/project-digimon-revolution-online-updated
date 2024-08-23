package com.example.api.controller;

import com.example.api.model.entity.Missao;
import com.example.api.service.MissaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/missao")
public class MissaoController {

    @Autowired
    private MissaoService missaoService;

    @PostMapping("/pegarRecompensa")
    public ResponseEntity<?> pegarRecompensa(@Valid @RequestBody Long idDigimon) {
        try {
            return ResponseEntity.ok(missaoService.resgatarRecompensaMissao(idDigimon));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/iniciarMissao")
    public ResponseEntity<?> iniciarMissao(@Valid @RequestBody Missao missao) {
        try {
            return ResponseEntity.ok(missaoService.iniciarMissao(missao));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

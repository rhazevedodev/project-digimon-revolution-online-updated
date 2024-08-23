package com.example.api.controller;

import com.example.api.model.request.RequestTrocarFragmentosPorBau;
import com.example.api.service.TrocasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trocas")
public class TrocasController {

    @Autowired
    private TrocasService trocasService;

    @PostMapping("/fragmento-bau")
    public ResponseEntity<?> trocarFragmentosBau(@Valid @RequestBody RequestTrocarFragmentosPorBau requestTrocarFragmentosPorBau) {
        try {
            return ResponseEntity.ok(trocasService.trocarFragmentosPorBau(requestTrocarFragmentosPorBau));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

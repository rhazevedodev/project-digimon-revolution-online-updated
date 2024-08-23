package com.example.api.controller;

import com.example.api.model.entity.Cacada;
import com.example.api.service.CacadaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cacada")
public class CacadaController {

    @Autowired
    private CacadaService cacadaService;

    @PostMapping("/resgatarRecompensa")
    public ResponseEntity<?> resgatarRecompensa(@RequestBody Long idDigimon) {
        try {
            return ResponseEntity.ok(cacadaService.resgatarRecompensa(idDigimon));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/verificarCacadaEmAndamento")
    public  ResponseEntity<Boolean> verificarCacadaEmAndamento(@RequestBody Long idDigimon) {
        boolean emAndamento = cacadaService.verificarCacadaEmAndamento(idDigimon);
        return ResponseEntity.ok(emAndamento);
    }

    @GetMapping("/verificarTempoDisponivel")
    public String testEndpoint(@RequestBody Long idDigimon) {
        return "O tempo disponível para caçada é de " + cacadaService.verificarTempoDisponivelCacada(idDigimon) + " minutos.";
    }

    @PostMapping("/iniciarCacada")
    public ResponseEntity<?> iniciarCacada(@Valid @RequestBody Cacada cacada) {
        try {
            return ResponseEntity.ok(cacadaService.iniciarCacada(cacada.getMinutos(), cacada.getIdDigimon()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

package com.example.api.controller;

import com.example.api.entity.Cacada;
import com.example.api.entity.dto.RequestCarregarTelaCacadas;
import com.example.api.service.CacadaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/telaCacada")
public class CacadaController {

    private static final Logger logger = LoggerFactory.getLogger(CacadaController.class);

    private final CacadaService cacadaService;

    public CacadaController(CacadaService cacadaService) {
        this.cacadaService = cacadaService;
    }
    @PostMapping("/carregar")
    public Map<String,Object> carregaTelaCacadas(@Valid @RequestBody RequestCarregarTelaCacadas request) {
        return cacadaService.carregarTelaCacada(request.getIdDigimon());
    }

    @PostMapping("/iniciarCacada")
    public ResponseEntity<?> iniciarCacada(@Valid @RequestBody Cacada cacada) {
        try {
            return ResponseEntity.ok(cacadaService.iniciarCacada(cacada.getMinutos(), cacada.getIdDigimon()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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


}

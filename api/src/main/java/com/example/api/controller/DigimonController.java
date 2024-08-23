package com.example.api.controller;

import com.example.api.model.entity.Digimon;
import com.example.api.service.DigimonService;
import com.example.api.service.JogadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/digimon")
public class DigimonController {

    @Autowired
    private DigimonService digimonService;
    @Autowired
    private JogadorService jogadorService;

    @PostMapping("/selecaoInicial")
    public ResponseEntity<?> selecionarDigimon(@Valid @RequestBody Digimon digimonSelecionado) {
        try {
            Digimon novoDigimon = digimonService.selecionarDigimon(digimonSelecionado);
            return ResponseEntity.ok(novoDigimon);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PostMapping("/verificaPrimeiroAcesso")
    public ResponseEntity<?> verificaPrimeiroAcesso(@RequestBody String usuario) {
        try {
            System.out.println("requisicao feita - verificaPrimeiroAcesso: usuario "+usuario);
            int idJogador = jogadorService.getIdByUsuario(usuario);
            boolean primeiroAcesso = digimonService.getDigimonByIdJogador(idJogador);
            if(!primeiroAcesso) {
                return ResponseEntity.ok(Map.of("primeiroAcesso", true));
//                return ResponseEntity.ok("primeiroAcesso");
            } else {
                return ResponseEntity.ok(Map.of("primeiroAcesso", false));
//                return ResponseEntity.ok("nãoPrimeiroAcesso");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

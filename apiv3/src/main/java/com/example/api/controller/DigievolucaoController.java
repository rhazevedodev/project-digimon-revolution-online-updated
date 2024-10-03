package com.example.api.controller;

import com.example.api.entity.Digievolucao;
import com.example.api.entity.dto.RequestDigievolucao;
import com.example.api.entity.dto.ResponseListarDigievolucoes;
import com.example.api.service.DigievolucaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/digievolucao")
public class DigievolucaoController {

    @Autowired
    private DigievolucaoService digievolucaoService;

    @GetMapping("/{idDigimon}")
    public ResponseEntity<List<ResponseListarDigievolucoes>> listarEvolucoes(@PathVariable int idDigimon) {
        List<ResponseListarDigievolucoes> evolucoes = digievolucaoService.getEvolucoes(idDigimon);
        return ResponseEntity.ok(evolucoes);
    }

    @PostMapping("/digievoluir")
    public ResponseEntity<String> digievolucao(@RequestBody RequestDigievolucao request) {
        digievolucaoService.digievolucao(request);
        return ResponseEntity.ok("Digievolucao para "+request.getEvolucaoEscolhida()+" salvo com sucesso!");
    }
}

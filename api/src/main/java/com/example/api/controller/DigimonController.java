package com.example.api.controller;

import com.example.api.model.entity.Digimon;
import com.example.api.model.request.RequestSelecaoInicial;
import com.example.api.service.DigimonService;
import com.example.api.service.JogadorService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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

    @GetMapping("/obterDigimons/{nomeUsuario}")
    public ResponseEntity<?> getDigimonByUsuario(@PathVariable String nomeUsuario) {
        try {
            System.out.println("getDigimonByUsuario");
            return ResponseEntity.ok(digimonService.getDigimonByUsuario(nomeUsuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter/")

    @PostMapping("/selecaoInicialv2")
    public ResponseEntity<?> selecionarDigimonv2(@RequestBody RequestSelecaoInicial request) {
        try {
              System.out.println(request.getNomeUsuario());
              System.out.println(request.getNomeDigimon());
              System.out.println(request.getApelidoDigimon());

            Digimon digimonSelecionado = new Digimon();
            digimonSelecionado.setIdJogador((long) jogadorService.getIdByUsuario(request.getNomeUsuario()));
            digimonSelecionado.setIdRookie(Integer.parseInt(digimonService.getIdByDescricao(request.getNomeDigimon())));
            digimonSelecionado.setNome(request.getApelidoDigimon());

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

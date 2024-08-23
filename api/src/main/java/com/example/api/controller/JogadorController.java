package com.example.api.controller;

import com.example.api.model.entity.Jogador;
import com.example.api.service.JogadorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jogador")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    public String testEndpoint() {
        return "Endpoint de teste funcionando!";
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarJogador(@Valid @RequestBody Jogador jogador, HttpServletRequest request) {
        try {
//            // Obtendo o endereço IP do cliente
//            String clientIp = request.getHeader("X-Forwarded-For");
//            if (clientIp == null || clientIp.isEmpty()) {
//                clientIp = request.getRemoteAddr();
//            }
//            // Opcional: você pode fazer algo com o IP do cliente, como registrar em logs, etc.
//            System.out.println("IP do cliente: " + clientIp);
            Jogador novoJogador = jogadorService.cadastrarJogador(jogador);
            return new ResponseEntity<>(novoJogador, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/buscar/primeiroAcesso")
//    public ResponseEntity<?> buscarPrimeiroAcesso(@RequestBody Jogador jogador) {
//        try {
//            Jogador jogadorBuscado = jogadorService.buscarPrimeiroAcesso(jogador);
//            return new ResponseEntity<>(jogadorBuscado, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

}

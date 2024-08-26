package com.example.api.controllerNova;

import com.example.api.modelNova.requests.RequestAutenticarUsuario;
import com.example.api.modelNova.requests.RequestVerificaPrimeiroAcesso;
import com.example.api.modelNova.responses.ResponseError;
import com.example.api.serviceNova.DigimonServiceNova;
import com.example.api.serviceNova.JogadorServiceNova;
import com.example.api.serviceNova.LoginServiceNova;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class TelaLoginController {

    @Autowired
    private LoginServiceNova loginService;
    @Autowired
    private JogadorServiceNova jogadorService;
    @Autowired
    private DigimonServiceNova digimonService;

    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticar(@RequestBody RequestAutenticarUsuario request) {
        if(request.getUsuario() == null || request.getSenha() == null) {
            return new ResponseEntity<>(new ResponseError("Usuário e senha são obrigatórios"), HttpStatus.BAD_REQUEST);
        }
        Optional<String> tokenOpt = loginService.autenticar(request.getUsuario(), request.getSenha());

        if (tokenOpt.isPresent()) {
            return ResponseEntity.ok(Map.of("token", tokenOpt.get()));
        } else {
            return new ResponseEntity<>(new ResponseError("Credenciais inválidas"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/verificaPrimeiroAcesso")
    public ResponseEntity<?> verificaPrimeiroAcesso(@RequestBody RequestVerificaPrimeiroAcesso request) {
        try {
            int idJogador = jogadorService.getIdByUsuario(loginService.decryptUsuario(request.getUsuario()));
            boolean primeiroAcesso = digimonService.getDigimonByIdJogador(idJogador);
            if(!primeiroAcesso) {
                return ResponseEntity.ok(Map.of("primeiroAcesso", true));
            } else {
                return ResponseEntity.ok(Map.of("primeiroAcesso", false));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/encryptUsuario/{usuario}")
    public ResponseEntity<?> encryptUsuario(@PathVariable("usuario") String usuario) {
        return ResponseEntity.ok(Map.of("usuario", loginService.encryptUsuario(usuario)));
    }

    @GetMapping("/decryptUsuario/{usuario}")
    public ResponseEntity<?> decryptUsuario(@PathVariable("usuario") String usuario) {
        return ResponseEntity.ok(Map.of("usuario", loginService.decryptUsuario(usuario)));
    }

}

package com.example.api.controllerNova;

import com.example.api.modelNova.requests.RequestAutenticarUsuario;
import com.example.api.modelNova.responses.ResponseError;
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

    @GetMapping("/encryptUsuario/{usuario}")
    public ResponseEntity<?> encryptUsuario(@PathVariable("usuario") String usuario) {
        return ResponseEntity.ok(Map.of("usuario", loginService.encryptUsuario(usuario)));
    }

}

package com.example.api.controllerNova;

import com.example.api.modelNova.requests.RequestAutenticarUsuario;
import com.example.api.serviceNova.LoginServiceNova;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class TelaLoginController {

    @Autowired
    private LoginServiceNova loginService;

    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticar(@RequestBody RequestAutenticarUsuario request) {
        String username = request.getUsuario();
        String password = request.getSenha();
        Optional<String> tokenOpt = loginService.autenticar(username, password);
        if (tokenOpt.isPresent()) {
            return ResponseEntity.ok(Map.of("token", tokenOpt.get()));
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

}

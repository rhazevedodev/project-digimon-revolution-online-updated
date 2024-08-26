package com.example.api.controllerNova;

import com.example.api.modelNova.requests.RequestAutenticarUsuario;
import com.example.api.modelNova.requests.RequestVerificaPrimeiroAcesso;
import com.example.api.modelNova.responses.ResponseError;
import com.example.api.serviceNova.DigimonServiceNova;
import com.example.api.serviceNova.JogadorServiceNova;
import com.example.api.serviceNova.LoginServiceNova;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class TelaLoginController {

    private static final Logger logger = LoggerFactory.getLogger(TelaLoginController.class);

    private final LoginServiceNova loginService;
    private final JogadorServiceNova jogadorService;
    private final DigimonServiceNova digimonService;

    @Autowired
    public TelaLoginController(LoginServiceNova loginService, JogadorServiceNova jogadorService, DigimonServiceNova digimonService) {
        this.loginService = loginService;
        this.jogadorService = jogadorService;
        this.digimonService = digimonService;
    }

    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticar(@Valid @RequestBody RequestAutenticarUsuario request) {
        logger.info("Autenticando usuário: {}", request.getUsuario());
        if (request.getUsuario() == null || request.getSenha() == null) {
            logger.warn("Usuário ou senha não fornecidos");
            return new ResponseEntity<>(new ResponseError("Usuário e senha são obrigatórios"), HttpStatus.BAD_REQUEST);
        }
        Optional<String> tokenOpt = loginService.autenticar(request.getUsuario(), request.getSenha());

        if (tokenOpt.isPresent()) {
            logger.info("Usuário autenticado com sucesso: {}", request.getUsuario());
            return ResponseEntity.ok(Map.of("token", tokenOpt.get()));
        } else {
            logger.warn("Credenciais inválidas para o usuário: {}", request.getUsuario());
            return new ResponseEntity<>(new ResponseError("Credenciais inválidas"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/verificaPrimeiroAcesso")
    public ResponseEntity<?> verificaPrimeiroAcesso(@Valid @RequestBody RequestVerificaPrimeiroAcesso request) {
        logger.info("Verificando primeiro acesso para o usuário: {}", request.getUsuario());
        try {
            int idJogador = jogadorService.getIdByUsuario(loginService.decryptUsuario(request.getUsuario()));
            boolean primeiroAcesso = digimonService.getDigimonByIdJogador(idJogador);
            return ResponseEntity.ok(Map.of("primeiroAcesso", !primeiroAcesso));
        } catch (Exception e) {
            logger.error("Erro ao verificar primeiro acesso para o usuário: {}", request.getUsuario(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/encryptUsuario/{usuario}")
    public ResponseEntity<?> encryptUsuario(@PathVariable("usuario") String usuario) {
        logger.info("Encrypting usuário: {}", usuario);
        return ResponseEntity.ok(Map.of("usuario", loginService.encryptUsuario(usuario)));
    }

    @GetMapping("/decryptUsuario/{usuario}")
    public ResponseEntity<?> decryptUsuario(@PathVariable("usuario") String usuario) {
        logger.info("Decrypting usuário: {}", usuario);
        return ResponseEntity.ok(Map.of("usuario", loginService.decryptUsuario(usuario)));
    }
}
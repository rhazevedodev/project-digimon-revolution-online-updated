package com.example.api.controllerNova;

import com.example.api.service.DigimonService;
import com.example.api.service.LoginService;
import com.example.api.serviceNova.LoginServiceNova;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/continuarJornada")
public class TelaContinuarJornadaController {

    @Autowired
    private DigimonService digimonService;
    @Autowired
    private LoginServiceNova loginService;

    @GetMapping("/obterDigimons/{nomeUsuario}")
    public ResponseEntity<?> getDigimonByUsuario(@PathVariable String nomeUsuario) {
        try {
            return ResponseEntity.ok(digimonService.getDigimonByUsuario(loginService.decryptUsuario(nomeUsuario)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

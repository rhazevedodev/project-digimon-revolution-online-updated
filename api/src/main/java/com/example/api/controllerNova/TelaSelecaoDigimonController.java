package com.example.api.controllerNova;

import com.example.api.model.entity.Digimon;
import com.example.api.model.request.RequestSelecaoInicial;
import com.example.api.service.DigimonService;
import com.example.api.service.JogadorService;
import com.example.api.serviceNova.LoginServiceNova;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/digimon")
public class TelaSelecaoDigimonController {

    @Autowired
    private DigimonService digimonService;
    @Autowired
    private JogadorService jogadorService;
    @Autowired
    private LoginServiceNova loginService;

    @PostMapping("/selecaoDigimon")
    public ResponseEntity<?> selecionarDigimonv2(@RequestBody RequestSelecaoInicial request) {
        try {
            Digimon digimonSelecionado = new Digimon();
            digimonSelecionado.setIdJogador((long) jogadorService.getIdByUsuario(loginService.decryptUsuario(request.getNomeUsuario())));
            digimonSelecionado.setIdRookie(Integer.parseInt(digimonService.getIdByDescricao(request.getNomeDigimon())));
            digimonSelecionado.setNome(request.getApelidoDigimon());

            Digimon novoDigimon = digimonService.selecionarDigimon(digimonSelecionado);
            return ResponseEntity.ok(novoDigimon);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
}

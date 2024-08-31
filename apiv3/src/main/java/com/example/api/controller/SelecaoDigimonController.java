package com.example.api.controller;

import com.example.api.entity.Digimon;
import com.example.api.entity.dto.RequestSelecaoDigimon;
import com.example.api.service.DigimonService;
import com.example.api.service.JogadorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/selecaoDigimon")
public class SelecaoDigimonController {

    private static final Logger logger = LoggerFactory.getLogger(SelecaoDigimonController.class);

    private final DigimonService digimonService;
    private final JogadorService jogadorService;

    public SelecaoDigimonController(DigimonService digimonService, JogadorService jogadorService) {
        this.digimonService = digimonService;
        this.jogadorService = jogadorService;
    }

    @PostMapping("/selecionar")
    public ResponseEntity<?> selecionarDigimon(@Valid @RequestBody RequestSelecaoDigimon request) {
        try {
            logger.info("Iniciando seleção de Digimon para o usuário: {}", request.getNomeUsuario());
            Digimon digimonSelecionado = new Digimon();
            digimonSelecionado.setIdJogador((long) jogadorService.getIdByUsuario(jogadorService.decryptUsuario(request.getNomeUsuario())));
            digimonSelecionado.setIdRookie(Integer.parseInt(digimonService.getIdByDescricao(request.getNomeDigimon())));
            if(digimonService.getNomeDigimon(request.getApelidoDigimon())){
                return new ResponseEntity<>(new RuntimeException("Esse apelido de digimon já foi escolhido, escolha outro!"), HttpStatus.BAD_REQUEST);
            } else {
                digimonSelecionado.setNome(request.getApelidoDigimon());
            }

            Digimon novoDigimon = digimonService.selecionarDigimon(digimonSelecionado);
            logger.info("Digimon selecionado com sucesso para o usuário: {}", request.getNomeUsuario());
            return ResponseEntity.ok(novoDigimon);
        } catch (Exception e) {
            logger.error("Erro ao selecionar Digimon para o usuário: {}", request.getNomeUsuario(), e);
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

}

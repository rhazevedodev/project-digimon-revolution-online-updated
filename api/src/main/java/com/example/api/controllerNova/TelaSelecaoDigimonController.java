package com.example.api.controllerNova;

import com.example.api.model.entity.Digimon;
import com.example.api.model.request.RequestSelecaoInicial;
import com.example.api.modelNova.requests.RequestSelecaoDigimon;
import com.example.api.modelNova.responses.ResponseError;
import com.example.api.service.DigimonService;
import com.example.api.service.JogadorService;
import com.example.api.serviceNova.DigimonServiceNova;
import com.example.api.serviceNova.JogadorServiceNova;
import com.example.api.serviceNova.LoginServiceNova;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/digimon")
public class TelaSelecaoDigimonController {

    private static final Logger logger = LoggerFactory.getLogger(TelaSelecaoDigimonController.class);

    private final DigimonServiceNova digimonService;
    private final JogadorServiceNova jogadorService;
    private final LoginServiceNova loginService;

    @Autowired
    public TelaSelecaoDigimonController(DigimonServiceNova digimonService, JogadorServiceNova jogadorService, LoginServiceNova loginService) {
        this.digimonService = digimonService;
        this.jogadorService = jogadorService;
        this.loginService = loginService;
    }

    @PostMapping("/selecaoDigimon")
    public ResponseEntity<?> selecionarDigimon(@Valid @RequestBody RequestSelecaoDigimon request) {
        try {
            logger.info("Iniciando seleção de Digimon para o usuário: {}", request.getNomeUsuario());
            Digimon digimonSelecionado = new Digimon();
            digimonSelecionado.setIdJogador((long) jogadorService.getIdByUsuario(loginService.decryptUsuario(request.getNomeUsuario())));
            digimonSelecionado.setIdRookie(Integer.parseInt(digimonService.getIdByDescricao(request.getNomeDigimon())));
            if(digimonService.getNomeDigimon(request.getApelidoDigimon())){
                return new ResponseEntity<>(new ResponseError("Esse apelido de digimon já foi escolhido, escolha outro!"), HttpStatus.BAD_REQUEST);
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

package com.example.api.controllerNova;

import com.example.api.model.entity.Jogador;
import com.example.api.modelNova.requests.RequestCadastrarUsuario;
import com.example.api.modelNova.responses.ResponseError;
import com.example.api.service.JogadorService;
import com.example.api.serviceNova.JogadorServiceNova;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@RestController
@RequestMapping("/api/cadastro")
public class TelaCadastroController {

    @Autowired
    private JogadorServiceNova jogadorService;

    @PostMapping("/usuario")
    public ResponseEntity<?> cadastrarJogador(@RequestBody Jogador jogador) {
        if(jogador.getUsuario().length() < 4 || jogador.getUsuario().length() > 20) {
            return new ResponseEntity<>(new ResponseError("O campo usuario deve ter entre 4 e 20 caracteres"), HttpStatus.BAD_REQUEST);
        }
        if(jogador.getSenha().length() < 6) {
            return new ResponseEntity<>(new ResponseError("O campo senha deve ter pelo menos 6 caracteres"), HttpStatus.BAD_REQUEST);
        }

        try {
            Jogador novoJogador = jogadorService.cadastrarJogador(jogador);
            return new ResponseEntity<>(novoJogador, HttpStatus.CREATED);

        } catch (Exception e) {
            ResponseError erroResposta = new ResponseError(e.getMessage());

            if (e.getMessage().equals("usuário já cadastrado")) {
                return new ResponseEntity<>(erroResposta, HttpStatus.IM_USED);
            }
            if (e.getMessage().equals("email já cadastrado")) {
                return new ResponseEntity<>(erroResposta, HttpStatus.IM_USED);
            }
            if (e.getMessage().equals("data de nascimento inválida")) {
                return new ResponseEntity<>(erroResposta, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(erroResposta, HttpStatus.BAD_REQUEST);
        }
    }

}

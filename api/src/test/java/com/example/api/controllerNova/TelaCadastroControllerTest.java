// src/test/java/com/example/api/controllerNova/TelaCadastroControllerTest.java
package com.example.api.controllerNova;

import com.example.api.model.entity.Jogador;
import com.example.api.modelNova.responses.ResponseError;
import com.example.api.serviceNova.JogadorServiceNova;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TelaCadastroControllerTest {

    @Mock
    private JogadorServiceNova jogadorService;

    @InjectMocks
    private TelaCadastroController telaCadastroController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarJogadorInvalidUsername() {
        Jogador jogador = new Jogador();
        jogador.setUsuario("abc"); // Invalid username length
        jogador.setSenha("validPassword");

        ResponseEntity<?> response = telaCadastroController.cadastrarJogador(jogador);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O campo usuario deve ter entre 4 e 20 caracteres", ((ResponseError) response.getBody()).getErro());
    }

    @Test
    void testCadastrarJogadorInvalidPassword() {
        Jogador jogador = new Jogador();
        jogador.setUsuario("validUsername");
        jogador.setSenha("123"); // Invalid password length

        ResponseEntity<?> response = telaCadastroController.cadastrarJogador(jogador);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O campo senha deve ter pelo menos 6 caracteres", ((ResponseError) response.getBody()).getErro());
    }

    @Test
    void testCadastrarJogadorSuccess() throws Exception {
        Jogador jogador = new Jogador();
        jogador.setUsuario("validUsername");
        jogador.setSenha("validPassword");

        Jogador novoJogador = new Jogador();
        when(jogadorService.cadastrarJogador(any(Jogador.class))).thenReturn(novoJogador);

        ResponseEntity<?> response = telaCadastroController.cadastrarJogador(jogador);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(novoJogador, response.getBody());
    }

    @Test
    void testCadastrarJogadorUsuarioJaCadastrado() throws Exception {
        Jogador jogador = new Jogador();
        jogador.setUsuario("validUsername");
        jogador.setSenha("validPassword");

        when(jogadorService.cadastrarJogador(any(Jogador.class))).thenThrow(new Exception("usuário já cadastrado"));

        ResponseEntity<?> response = telaCadastroController.cadastrarJogador(jogador);

        assertEquals(HttpStatus.IM_USED, response.getStatusCode());
        assertEquals("usuário já cadastrado", ((ResponseError) response.getBody()).getErro());
    }

    @Test
    void testCadastrarJogadorEmailJaCadastrado() throws Exception {
        Jogador jogador = new Jogador();
        jogador.setUsuario("validUsername");
        jogador.setSenha("validPassword");

        when(jogadorService.cadastrarJogador(any(Jogador.class))).thenThrow(new Exception("email já cadastrado"));

        ResponseEntity<?> response = telaCadastroController.cadastrarJogador(jogador);

        assertEquals(HttpStatus.IM_USED, response.getStatusCode());
        assertEquals("email já cadastrado", ((ResponseError) response.getBody()).getErro());
    }

    @Test
    void testCadastrarJogadorDataNascimentoInvalida() throws Exception {
        Jogador jogador = new Jogador();
        jogador.setUsuario("validUsername");
        jogador.setSenha("validPassword");

        when(jogadorService.cadastrarJogador(any(Jogador.class))).thenThrow(new Exception("data de nascimento inválida"));

        ResponseEntity<?> response = telaCadastroController.cadastrarJogador(jogador);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("data de nascimento inválida", ((ResponseError) response.getBody()).getErro());
    }
}
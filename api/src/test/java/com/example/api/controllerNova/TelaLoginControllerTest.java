package com.example.api.controllerNova;
import com.example.api.modelNova.requests.RequestAutenticarUsuario;
import com.example.api.modelNova.responses.ResponseError;
import com.example.api.serviceNova.LoginServiceNova;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TelaLoginControllerTest {

    @Mock
    private LoginServiceNova loginService;

    @InjectMocks
    private TelaLoginController telaLoginController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAutenticar_Success() {
        // Arrange
        RequestAutenticarUsuario request = new RequestAutenticarUsuario();
        request.setUsuario("validUser");
        request.setSenha("validPassword");
        when(loginService.autenticar("validUser", "validPassword")).thenReturn(Optional.of("validToken"));

        // Act
        ResponseEntity<?> response = telaLoginController.autenticar(request);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("validToken", ((Map<String, String>) response.getBody()).get("token"));
    }

    @Test
    public void testAutenticar_Failure() {
        // Arrange
        RequestAutenticarUsuario request = new RequestAutenticarUsuario();
        request.setUsuario("invalidUser");
        request.setSenha("invalidPassword");
        when(loginService.autenticar("invalidUser", "invalidPassword")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = telaLoginController.autenticar(request);

        // Assert
        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Credenciais inválidas", ((ResponseError) response.getBody()).getErro());
    }

    @Test
    public void testAutenticar_MissingUsernameOrPassword() {
        // Arrange
        RequestAutenticarUsuario request = new RequestAutenticarUsuario();
        request.setUsuario(null);
        request.setSenha("somePassword");

        // Act
        ResponseEntity<?> response = telaLoginController.autenticar(request);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Usuário e senha são obrigatórios", ((ResponseError) response.getBody()).getErro());

        // Arrange
        request.setUsuario("someUser");
        request.setSenha(null);

        // Act
        response = telaLoginController.autenticar(request);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Usuário e senha são obrigatórios", ((ResponseError) response.getBody()).getErro());
    }
}
package com.example.api.modelNova.requests;

import jakarta.validation.constraints.NotBlank;

public class RequestVerificaPrimeiroAcesso {

    @NotBlank(message = "Nome de usuário é obrigatório")
    String usuario;

    public RequestVerificaPrimeiroAcesso() {
    }

    public RequestVerificaPrimeiroAcesso(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

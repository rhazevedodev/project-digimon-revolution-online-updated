package com.example.api.entity.dto;

import jakarta.validation.constraints.NotBlank;

public class RequestVerificarPrimeiroAcesso {

    @NotBlank(message = "Nome de usuário é obrigatório")
    String usuario;

    public RequestVerificarPrimeiroAcesso() {
    }

    public RequestVerificarPrimeiroAcesso(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

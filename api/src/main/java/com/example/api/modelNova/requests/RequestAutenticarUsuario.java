package com.example.api.modelNova.requests;

import jakarta.validation.constraints.NotBlank;

public class RequestAutenticarUsuario {

    @NotBlank(message = "Nome de usuário é obrigatório")
    private String usuario;
    @NotBlank(message = "Senha é obrigatória")
    private String senha;

    public RequestAutenticarUsuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public RequestAutenticarUsuario() {

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

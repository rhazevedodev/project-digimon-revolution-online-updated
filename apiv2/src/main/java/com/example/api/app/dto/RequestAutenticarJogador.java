package com.example.api.app.dto;

public class RequestAutenticarJogador {

    private String usuario;
    private String senha;

    public RequestAutenticarJogador() {
    }

    public RequestAutenticarJogador(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
}

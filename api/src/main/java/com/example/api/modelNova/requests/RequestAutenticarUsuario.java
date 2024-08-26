package com.example.api.modelNova.requests;

public class RequestAutenticarUsuario {

    private String usuario;
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

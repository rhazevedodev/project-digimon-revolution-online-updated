package com.example.api.modelNova.requests;

public class RequestVerificaPrimeiroAcesso {

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

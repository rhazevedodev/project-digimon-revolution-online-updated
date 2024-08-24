package com.example.api.model.request;

public class RequestSelecaoInicial {

    String nomeUsuario;
    String nomeDigimon;
    String apelidoDigimon;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeDigimon() {
        return nomeDigimon;
    }

    public void setNomeDigimon(String nomeDigimon) {
        this.nomeDigimon = nomeDigimon;
    }

    public String getApelidoDigimon() {
        return apelidoDigimon;
    }

    public void setApelidoDigimon(String apelidoDigimon) {
        this.apelidoDigimon = apelidoDigimon;
    }
}

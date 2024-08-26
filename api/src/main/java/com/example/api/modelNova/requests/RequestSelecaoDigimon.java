package com.example.api.modelNova.requests;

import jakarta.validation.constraints.NotBlank;

public class RequestSelecaoDigimon {

    @NotBlank(message = "Nome de usuário é obrigatório")
    String nomeUsuario;

    @NotBlank(message = "Nome do Digimon é obrigatório")
    String nomeDigimon;

    @NotBlank(message = "Apelido do Digimon é obrigatório")
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

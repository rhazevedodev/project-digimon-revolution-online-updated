package com.example.api.entity.dto;

public class RequestDigievolucao {

    private int idDigimon;
    private String evolucaoEscolhida;

    public RequestDigievolucao() {
    }

    public RequestDigievolucao(int idDigimon, String evolucaoEscolhida) {
        this.idDigimon = idDigimon;
        this.evolucaoEscolhida = evolucaoEscolhida;
    }

    public int getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(int idDigimon) {
        this.idDigimon = idDigimon;
    }

    public String getEvolucaoEscolhida() {
        return evolucaoEscolhida;
    }

    public void setEvolucaoEscolhida(String evolucaoEscolhida) {
        this.evolucaoEscolhida = evolucaoEscolhida;
    }
}

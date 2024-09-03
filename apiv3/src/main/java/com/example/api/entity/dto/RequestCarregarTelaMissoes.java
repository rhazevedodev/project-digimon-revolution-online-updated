package com.example.api.entity.dto;

public class RequestCarregarTelaMissoes {

    private Long idDigimon;

    public RequestCarregarTelaMissoes() {
    }

    public RequestCarregarTelaMissoes(Long idDigimon) {
        this.idDigimon = idDigimon;
    }

    public Long getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(Long idDigimon) {
        this.idDigimon = idDigimon;
    }
}

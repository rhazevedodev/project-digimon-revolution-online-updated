package com.example.api.entity.dto;

public class RequestCarregarTelaCacadas {

    Long idDigimon;

    public RequestCarregarTelaCacadas() {
    }

    public RequestCarregarTelaCacadas(Long idDigimon) {
        this.idDigimon = idDigimon;
    }

    public Long getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(Long idDigimon) {
        this.idDigimon = idDigimon;
    }
}

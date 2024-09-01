package com.example.api.entity.dto;

public class RequestCarregarTelaStatus {

    Long idDigimon;

    public RequestCarregarTelaStatus() {
    }

    public RequestCarregarTelaStatus(Long idDigimon) {
        this.idDigimon = idDigimon;
    }

    public Long getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(Long idDigimon) {
        this.idDigimon = idDigimon;
    }
}

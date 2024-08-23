package com.example.api.model.request;

public class RequestBuscarPeriodoPremium {

    private Long idDigimon;

    public RequestBuscarPeriodoPremium() {
    }

    public RequestBuscarPeriodoPremium(Long idDigimon) {
        this.idDigimon = idDigimon;
    }

    public Long getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(Long idDigimon) {
        this.idDigimon = idDigimon;
    }
}

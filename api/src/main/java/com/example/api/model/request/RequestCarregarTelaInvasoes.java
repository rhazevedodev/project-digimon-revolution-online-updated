package com.example.api.model.request;

public class RequestCarregarTelaInvasoes {

    Long idDigimon;

    public RequestCarregarTelaInvasoes() {
    }

    public RequestCarregarTelaInvasoes(Long idDigimon) {
        this.idDigimon = idDigimon;
    }

    public Long getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(Long idDigimon) {
        this.idDigimon = idDigimon;
    }
}

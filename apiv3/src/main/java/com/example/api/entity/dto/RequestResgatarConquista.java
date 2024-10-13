package com.example.api.entity.dto;

public class RequestResgatarConquista {

    private Long idConquista;
    private Long idDigimon;

    public RequestResgatarConquista() {
    }

    public RequestResgatarConquista(Long idConquista, Long idDigimon) {
        this.idConquista = idConquista;
        this.idDigimon = idDigimon;
    }

    public Long getIdConquista() {
        return idConquista;
    }

    public void setIdConquista(Long idConquista) {
        this.idConquista = idConquista;
    }

    public Long getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(Long idDigimon) {
        this.idDigimon = idDigimon;
    }
}

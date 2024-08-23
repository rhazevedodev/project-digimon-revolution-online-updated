package com.example.api.model.request;

public class RequestIniciarCombateInvasao {

    long idDigimonAtacante;

    public RequestIniciarCombateInvasao() {
    }

    public RequestIniciarCombateInvasao(long idDigimonAtacante) {
        this.idDigimonAtacante = idDigimonAtacante;
    }

    public long getIdDigimonAtacante() {
        return idDigimonAtacante;
    }

    public void setIdDigimonAtacante(long idDigimonAtacante) {
        this.idDigimonAtacante = idDigimonAtacante;
    }

}

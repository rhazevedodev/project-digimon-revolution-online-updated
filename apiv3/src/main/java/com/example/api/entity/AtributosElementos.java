package com.example.api.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class AtributosElementos {

    @NotNull
    private int elementoPrimitivo;

    private int pontosElementoPrimitivo;

    public AtributosElementos() {
    }

    public AtributosElementos(int elementoPrimitivo, int pontosElementoPrimitivo) {
        this.elementoPrimitivo = elementoPrimitivo;
        this.pontosElementoPrimitivo = pontosElementoPrimitivo;

    }

    public int getElementoPrimitivo() {
        return elementoPrimitivo;
    }

    public void setElementoPrimitivo(int elementoPrimitivo) {
        this.elementoPrimitivo = elementoPrimitivo;
    }

    public int getPontosElementoPrimitivo() {
        return pontosElementoPrimitivo;
    }

    public void setPontosElementoPrimitivo(int pontosElementoPrimitivo) {
        this.pontosElementoPrimitivo = pontosElementoPrimitivo;
    }

}

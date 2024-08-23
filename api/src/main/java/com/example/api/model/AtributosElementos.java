package com.example.api.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class AtributosElementos {

    @NotNull
    private int elementoPrimitivo;

    private int pontosElementoPrimitivo;

    private int fogo;

    private int agua;

    private int vento;

    private int gelo;

    private int raio;

    private int planta;

    private int terra;

    private int metal;

    private int terras;

    private int luz;

    public AtributosElementos() {
    }

    public AtributosElementos(int elementoPrimitivo, int pontosElementoPrimitivo, int fogo, int agua, int vento, int gelo, int raio, int planta, int terra, int metal, int terras, int luz) {
        this.elementoPrimitivo = elementoPrimitivo;
        this.pontosElementoPrimitivo = pontosElementoPrimitivo;
        this.fogo = fogo;
        this.agua = agua;
        this.vento = vento;
        this.gelo = gelo;
        this.raio = raio;
        this.planta = planta;
        this.terra = terra;
        this.metal = metal;
        this.terras = terras;
        this.luz = luz;
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

    public int getFogo() {
        return fogo;
    }

    public void setFogo(int fogo) {
        this.fogo = fogo;
    }

    public int getAgua() {
        return agua;
    }

    public void setAgua(int agua) {
        this.agua = agua;
    }

    public int getVento() {
        return vento;
    }

    public void setVento(int vento) {
        this.vento = vento;
    }

    public int getGelo() {
        return gelo;
    }

    public void setGelo(int gelo) {
        this.gelo = gelo;
    }

    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
        this.raio = raio;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }

    public int getTerra() {
        return terra;
    }

    public void setTerra(int terra) {
        this.terra = terra;
    }

    public int getMetal() {
        return metal;
    }

    public void setMetal(int metal) {
        this.metal = metal;
    }

    public int getTerras() {
        return terras;
    }

    public void setTerras(int terras) {
        this.terras = terras;
    }

    public int getLuz() {
        return luz;
    }

    public void setLuz(int luz) {
        this.luz = luz;
    }
}

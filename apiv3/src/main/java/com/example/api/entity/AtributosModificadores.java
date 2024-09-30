package com.example.api.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class AtributosModificadores {

    private int modificadorVida = 0;

    private int modificadorEnergia = 0;

    private int modificadorForca = 0;

    private int modificadorInteligencia = 0;

    private int modificadorAgilidade = 0;

    private int modificadorConhecimento = 0;

    public AtributosModificadores() {
    }

    public AtributosModificadores(int modificadorVida, int modificadorEnergia, int modificadorForca, int modificadorInteligencia, int modificadorAgilidade, int modificadorConhecimento) {
        this.modificadorVida = modificadorVida;
        this.modificadorEnergia = modificadorEnergia;
        this.modificadorForca = modificadorForca;
        this.modificadorInteligencia = modificadorInteligencia;
        this.modificadorAgilidade = modificadorAgilidade;
        this.modificadorConhecimento = modificadorConhecimento;
    }

    public int getModificadorVida() {
        return modificadorVida;
    }

    public void setModificadorVida(int modificadorVida) {
        this.modificadorVida = modificadorVida;
    }

    public int getModificadorEnergia() {
        return modificadorEnergia;
    }

    public void setModificadorEnergia(int modificadorEnergia) {
        this.modificadorEnergia = modificadorEnergia;
    }

    public int getModificadorForca() {
        return modificadorForca;
    }

    public void setModificadorForca(int modificadorForca) {
        this.modificadorForca = modificadorForca;
    }

    public int getModificadorInteligencia() {
        return modificadorInteligencia;
    }

    public void setModificadorInteligencia(int modificadorInteligencia) {
        this.modificadorInteligencia = modificadorInteligencia;
    }

    public int getModificadorAgilidade() {
        return modificadorAgilidade;
    }

    public void setModificadorAgilidade(int modificadorAgilidade) {
        this.modificadorAgilidade = modificadorAgilidade;
    }

    public int getModificadorConhecimento() {
        return modificadorConhecimento;
    }

    public void setModificadorConhecimento(int modificadorConhecimento) {
        this.modificadorConhecimento = modificadorConhecimento;
    }
}

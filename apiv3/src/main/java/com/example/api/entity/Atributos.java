package com.example.api.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Atributos {

    private int pontosVida = 50;

    private int pontosForca = 1;

    private int pontosInteligencia = 1;

    private int pontosAgilidade = 1;

    private int pontosConhecimento = 1;

    public Atributos() {
    }

    public Atributos(int pontosVida, int pontosForca, int pontosInteligencia, int pontosAgilidade, int pontosConhecimento) {
        this.pontosVida = pontosVida;
        this.pontosForca = pontosForca;
        this.pontosInteligencia = pontosInteligencia;
        this.pontosAgilidade = pontosAgilidade;
        this.pontosConhecimento = pontosConhecimento;
    }

    public int getPontosVida() {
        return pontosVida;
    }

    public void setPontosVida(int pontosVida) {
        this.pontosVida = pontosVida;
    }

    public int getPontosForca() {
        return pontosForca;
    }

    public void setPontosForca(int pontosForca) {
        this.pontosForca = pontosForca;
    }

    public int getPontosInteligencia() {
        return pontosInteligencia;
    }

    public void setPontosInteligencia(int pontosInteligencia) {
        this.pontosInteligencia = pontosInteligencia;
    }

    public int getPontosAgilidade() {
        return pontosAgilidade;
    }

    public void setPontosAgilidade(int pontosAgilidade) {
        this.pontosAgilidade = pontosAgilidade;
    }

    public int getPontosConhecimento() {
        return pontosConhecimento;
    }

    public void setPontosConhecimento(int pontosConhecimento) {
        this.pontosConhecimento = pontosConhecimento;
    }

    public int getTotalAtaqueFisico() {
        return this.pontosForca * 2 + this.pontosAgilidade;
    }

    public int getTotalDefesaFisica(){
        return (int) (this.pontosForca * 0.5 + this.pontosAgilidade);
    }

    public int getTotalAtaqueElemental(){
        return this.pontosInteligencia * 2 + this.pontosConhecimento;
    }

    public int getTotalDefesaElemental(){
        return (int) (this.pontosInteligencia * 0.5 + this.pontosConhecimento);
    }


}


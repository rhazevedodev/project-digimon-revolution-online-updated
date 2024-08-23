package com.example.api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Invasao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int idInvasor;

    private String nomeInvasor;

    private int energiaVital;

    private LocalDateTime dataInvasao;

    private int ataquesSofridos;

    private Long derrotadoPor;

    private LocalDateTime derrotadoEm;

    private boolean derrotado;

    public Invasao() {
    }

    public Invasao(Long id, int idInvasor, int energiaVital, String nomeInvasor, LocalDateTime dataInvasao, int ataquesSofridos, Long derrotadoPor, LocalDateTime derrotadoEm, boolean derrotado) {
        this.id = id;
        this.idInvasor = idInvasor;
        this.energiaVital = energiaVital;
        this.nomeInvasor = nomeInvasor;
        this.dataInvasao = dataInvasao;
        this.ataquesSofridos = ataquesSofridos;
        this.derrotadoPor = derrotadoPor;
        this.derrotadoEm = derrotadoEm;
        this.derrotado = derrotado;
    }

    public int getEnergiaVital() {
        return energiaVital;
    }

    public void setEnergiaVital(int energiaVital) {
        this.energiaVital = energiaVital;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getIdInvasor() {
        return idInvasor;
    }

    public void setIdInvasor(int idInvasor) {
        this.idInvasor = idInvasor;
    }

    public String getNomeInvasor() {
        return nomeInvasor;
    }

    public void setNomeInvasor(String nomeInvasor) {
        this.nomeInvasor = nomeInvasor;
    }

    public LocalDateTime getDataInvasao() {
        return dataInvasao;
    }

    public void setDataInvasao(LocalDateTime dataInvasao) {
        this.dataInvasao = dataInvasao;
    }

    public int getAtaquesSofridos() {
        return ataquesSofridos;
    }

    public void setAtaquesSofridos(int ataquesSofridos) {
        this.ataquesSofridos = ataquesSofridos;
    }

    public Long getDerrotadoPor() {
        return derrotadoPor;
    }

    public void setDerrotadoPor(Long derrotadoPor) {
        this.derrotadoPor = derrotadoPor;
    }

    public LocalDateTime getDerrotadoEm() {
        return derrotadoEm;
    }

    public void setDerrotadoEm(LocalDateTime derrotadoEm) {
        this.derrotadoEm = derrotadoEm;
    }

    public boolean isDerrotado() {
        return derrotado;
    }

    public void setDerrotado(boolean derrotado) {
        this.derrotado = derrotado;
    }
}

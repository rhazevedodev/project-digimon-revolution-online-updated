package com.example.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TempoDisponivelCacada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo idDigimon é obrigatório")
    private long idDigimon;

    private LocalDate dataCadastro = LocalDate.now();

    private int tempoDisponivel;

    private LocalDateTime dataUltimaAlteracao;

    public TempoDisponivelCacada() {
    }

    public TempoDisponivelCacada(long idDigimon, int tempoDisponivel, LocalDateTime dataUltimaAlteracao) {
        this.idDigimon = idDigimon;
        this.tempoDisponivel = tempoDisponivel;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public TempoDisponivelCacada(Long id, long idDigimon, int tempoDisponivel, LocalDateTime dataUltimaAlteracao) {
        this.id = id;
        this.idDigimon = idDigimon;
        this.tempoDisponivel = tempoDisponivel;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public TempoDisponivelCacada(long idDigimon, LocalDate dataCadastro, int tempoDisponivel) {
        this.idDigimon = idDigimon;
        this.dataCadastro = dataCadastro;
        this.tempoDisponivel = tempoDisponivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(long idDigimon) {
        this.idDigimon = idDigimon;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public int getTempoDisponivel() {
        return tempoDisponivel;
    }

    public void setTempoDisponivel(int tempoDisponivel) {
        this.tempoDisponivel = tempoDisponivel;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
}

package com.example.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Cacada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long idDigimon;

    private LocalDate data = LocalDate.now();

    private int minutos;

    private LocalDateTime horaResgateDisponivel;

    private boolean recompensaResgatada;

    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    public Cacada() {
    }

    public Cacada(Long idDigimon, int minutos, LocalDateTime horaResgateDisponivel, boolean recompensaResgatada) {
        this.idDigimon = idDigimon;
        this.minutos = minutos;
        this.horaResgateDisponivel = horaResgateDisponivel;
        this.recompensaResgatada = recompensaResgatada;
    }

    public Cacada(Long idDigimon, int minutos, LocalDateTime horaResgateDisponivel, boolean recompensaResgatada, LocalDateTime ultimaAlteracao) {
        this.idDigimon = idDigimon;
        this.minutos = minutos;
        this.horaResgateDisponivel = horaResgateDisponivel;
        this.recompensaResgatada = recompensaResgatada;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(Long idDigimon) {
        this.idDigimon = idDigimon;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public LocalDateTime getHoraResgateDisponivel() {
        return horaResgateDisponivel;
    }

    public void setHoraResgateDisponivel(LocalDateTime horaResgateDisponivel) {
        this.horaResgateDisponivel = horaResgateDisponivel;
    }

    public boolean isRecompensaResgatada() {
        return recompensaResgatada;
    }

    public void setRecompensaResgatada(boolean recompensaResgatada) {
        this.recompensaResgatada = recompensaResgatada;
    }

    public LocalDateTime getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(LocalDateTime ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }
}

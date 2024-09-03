package com.example.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Missao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idDigimon;

    private int idMissao;

    private int qtHoras;

    private LocalDate data;

    private LocalDateTime horaResgateDisponivel;

    private boolean recompensaResgatada;

    private LocalDateTime ultimaAlteracao;

    public Missao() {
    }

    public Missao(Long idDigimon, int idMissao, int qtHoras, LocalDate data, LocalDateTime horaResgateDisponivel, boolean recompensaResgatada, LocalDateTime ultimaAlteracao) {
        this.idDigimon = idDigimon;
        this.idMissao = idMissao;
        this.qtHoras = qtHoras;
        this.data = data;
        this.horaResgateDisponivel = horaResgateDisponivel;
        this.recompensaResgatada = recompensaResgatada;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Missao(Long id, Long idDigimon, int idMissao, int qtHoras, LocalDate data, LocalDateTime horaResgateDisponivel,
                  boolean recompensaResgatada, LocalDateTime ultimaAlteracao) {
        this.id = id;
        this.idDigimon = idDigimon;
        this.idMissao = idMissao;
        this.qtHoras = qtHoras;
        this.data = data;
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

    public int getIdMissao() {
        return idMissao;
    }

    public void setIdMissao(int idMissao) {
        this.idMissao = idMissao;
    }

    public int getQtHoras() {
        return qtHoras;
    }

    public void setQtHoras(int qtHoras) {
        this.qtHoras = qtHoras;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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

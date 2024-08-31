package com.example.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHoraRegistro = LocalDateTime.now();

    @NotBlank(message = "O campo ação é obrigatório")
    private String acao;

    @NotBlank(message = "O campo detalhes é obrigatório")
    private String detalhes;

    public Log() {
    }

    public Log(String acao, String detalhes) {
        this.acao = acao;
        this.detalhes = detalhes;
    }

    public Log(Long id, LocalDateTime dataHoraRegistro, String acao, String detalhes) {
        this.id = id;
        this.dataHoraRegistro = dataHoraRegistro;
        this.acao = acao;
        this.detalhes = detalhes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(LocalDateTime dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}

package com.example.api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class RegistroConquistas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idDigimon;

    private Long idJogador;

    private int bitsObtidos;

    private int expObtida;

    private int cacadasConcluidas;

    private int missoesConcluidas;

    private int niveisTotais;

    private int fragmentosObtidos;

    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    public RegistroConquistas() {
    }

    public RegistroConquistas(Long id, Long idDigimon, Long idJogador, int bitsObtidos, int expObtida, int cacadasConcluidas, int missoesConcluidas, int niveisTotais, int fragmentosObtidos, LocalDateTime ultimaAlteracao) {
        this.id = id;
        this.idDigimon = idDigimon;
        this.idJogador = idJogador;
        this.bitsObtidos = bitsObtidos;
        this.expObtida = expObtida;
        this.cacadasConcluidas = cacadasConcluidas;
        this.missoesConcluidas = missoesConcluidas;
        this.niveisTotais = niveisTotais;
        this.fragmentosObtidos = fragmentosObtidos;
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

    public Long getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Long idJogador) {
        this.idJogador = idJogador;
    }

    public int getBitsObtidos() {
        return bitsObtidos;
    }

    public void setBitsObtidos(int bitsObtidos) {
        this.bitsObtidos = bitsObtidos;
    }

    public int getExpObtida() {
        return expObtida;
    }

    public void setExpObtida(int expObtida) {
        this.expObtida = expObtida;
    }

    public int getCacadasConcluidas() {
        return cacadasConcluidas;
    }

    public void setCacadasConcluidas(int cacadasConcluidas) {
        this.cacadasConcluidas = cacadasConcluidas;
    }

    public int getMissoesConcluidas() {
        return missoesConcluidas;
    }

    public void setMissoesConcluidas(int missoesConcluidas) {
        this.missoesConcluidas = missoesConcluidas;
    }

    public int getNiveisTotais() {
        return niveisTotais;
    }

    public void setNiveisTotais(int niveisTotais) {
        this.niveisTotais = niveisTotais;
    }

    public int getFragmentosObtidos() {
        return fragmentosObtidos;
    }

    public void setFragmentosObtidos(int fragmentosObtidos) {
        this.fragmentosObtidos = fragmentosObtidos;
    }

    public LocalDateTime getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(LocalDateTime ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }
}

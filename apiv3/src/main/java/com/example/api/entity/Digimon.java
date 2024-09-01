package com.example.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Digimon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo idJogador é obrigatório")
    private Long idJogador;

    private int idRookie;

    private int idChampion;

    private int idUltimate;

    private int idMega;

    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 4, max = 20, message = "O campo nome deve ter entre 4 e 20 caracteres")
    private String nome;

    private int nivel = 1;

    private int bits = 100;

    private int diamantes;

    private int pontosExperiencia;

    @Embedded
    private Atributos atributos;

    @Embedded
    private AtributosElementos atributosElementos;

    private LocalDate dataCadastro = LocalDate.now();

    private LocalDateTime dataUltimaAlteracao;

    private boolean bonusExperienciaAtivo;

    private boolean bonusBitsAtivo;

    public Digimon() {
    }

    public Digimon(Long id, Long idJogador, int idRookie, int idChampion, int idUltimate, int idMega, String nome, int nivel, int bits, int diamantes, int pontosExperiencia, Atributos atributos, AtributosElementos atributosElementos, LocalDate dataCadastro, LocalDateTime dataUltimaAlteracao, Boolean bonusExperienciaAtivo, Boolean bonusBitsAtivo) {
        this.id = id;
        this.idJogador = idJogador;
        this.idRookie = idRookie;
        this.idChampion = idChampion;
        this.idUltimate = idUltimate;
        this.idMega = idMega;
        this.nome = nome;
        this.nivel = nivel;
        this.bits = bits;
        this.diamantes = diamantes;
        this.pontosExperiencia = pontosExperiencia;
        this.atributos = atributos;
        this.atributosElementos = atributosElementos;
        this.dataCadastro = dataCadastro;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.bonusExperienciaAtivo = bonusExperienciaAtivo;
        this.bonusBitsAtivo = bonusBitsAtivo;
    }

    public boolean isBonusExperienciaAtivo() {
        return bonusExperienciaAtivo;
    }

    public void setBonusExperienciaAtivo(boolean bonusExperienciaAtivo) {
        this.bonusExperienciaAtivo = bonusExperienciaAtivo;
    }

    public boolean isBonusBitsAtivo() {
        return bonusBitsAtivo;
    }

    public void setBonusBitsAtivo(boolean bonusBitsAtivo) {
        this.bonusBitsAtivo = bonusBitsAtivo;
    }

    public int getDiamantes() {
        return diamantes;
    }

    public void setDiamantes(int diamantes) {
        this.diamantes = diamantes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Long idJogador) {
        this.idJogador = idJogador;
    }

    public int getIdRookie() {
        return idRookie;
    }

    public void setIdRookie(int idRookie) {
        this.idRookie = idRookie;
    }

    public int getIdChampion() {
        return idChampion;
    }

    public void setIdChampion(int idChampion) {
        this.idChampion = idChampion;
    }

    public int getIdMega() {
        return idMega;
    }

    public void setIdMega(int idMega) {
        this.idMega = idMega;
    }

    public int getIdUltimate() {
        return idUltimate;
    }

    public void setIdUltimate(int idUltimate) {
        this.idUltimate = idUltimate;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getBits() {
        return bits;
    }

    public void setBits(int bits) {
        this.bits = bits;
    }

    public int getPontosExperiencia() {
        return pontosExperiencia;
    }

    public void setPontosExperiencia(int pontosExperiencia) {
        this.pontosExperiencia = pontosExperiencia;
    }

    public Atributos getAtributos() {
        return atributos;
    }

    public void setAtributos(Atributos atributos) {
        this.atributos = atributos;
    }

    public AtributosElementos getAtributosElementos() {
        return atributosElementos;
    }

    public void setAtributosElementos(AtributosElementos atributosElementos) {
        this.atributosElementos = atributosElementos;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
}

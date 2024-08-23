package com.example.api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idDigimon;

    private int idItem;

    private String descricaoItem;

    private int quantidade;

    private boolean isFragmento;

    private boolean isConsumivel;

//    private boolean isEquipamento;
//
//    private boolean isVenda;
//
//    private boolean isTroca;

    private LocalDateTime dataUltimaAlteracao;

    public Inventario() {
    }

    public Inventario(Long idDigimon, int idItem, String descricaoItem, int quantidade, boolean isFragmento, LocalDateTime dataUltimaAlteracao) {
        this.idDigimon = idDigimon;
        this.idItem = idItem;
        this.descricaoItem = descricaoItem;
        this.quantidade = quantidade;
        this.isFragmento = isFragmento;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public Inventario(Long id, Long idDigimon, int idItem, String descricaoItem, int quantidade, boolean isFragmento, boolean isConsumivel, LocalDateTime dataUltimaAlteracao) {
        this.id = id;
        this.idDigimon = idDigimon;
        this.idItem = idItem;
        this.descricaoItem = descricaoItem;
        this.quantidade = quantidade;
        this.isFragmento = isFragmento;
        this.isConsumivel = isConsumivel;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
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

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isFragmento() {
        return isFragmento;
    }

    public void setFragmento(boolean fragmento) {
        isFragmento = fragmento;
    }

    public boolean isConsumivel() {
        return isConsumivel;
    }

    public void setConsumivel(boolean consumivel) {
        isConsumivel = consumivel;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
}

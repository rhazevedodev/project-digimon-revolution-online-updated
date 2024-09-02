package com.example.api.entity;

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

    private String nomeItem;

    private String descricaoItem;

    private int quantidade;

    private int idCategoria;

    private int valorCompra;

    private int valorVenda;

//    private boolean isFragmento;

//    private boolean isConsumivel;

//    private boolean isEquipamento;

    private boolean equipado;

    private boolean podeVender;

    private boolean podeTrocar;

    private LocalDateTime dataUltimaAlteracao;

    public Inventario() {
    }

    public Inventario(Long id, Long idDigimon, int idItem, String nomeItem, String descricaoItem, int quantidade, int idCategoria, int valorCompra, int valorVenda, boolean equipado, boolean podeVender, boolean podeTrocar, LocalDateTime dataUltimaAlteracao) {
        this.id = id;
        this.idDigimon = idDigimon;
        this.idItem = idItem;
        this.nomeItem = nomeItem;
        this.descricaoItem = descricaoItem;
        this.quantidade = quantidade;
        this.idCategoria = idCategoria;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.equipado = equipado;
        this.podeVender = podeVender;
        this.podeTrocar = podeTrocar;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public boolean isEquipado() {
        return equipado;
    }

    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
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

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public boolean isPodeVender() {
        return podeVender;
    }

    public void setPodeVender(boolean podeVender) {
        this.podeVender = podeVender;
    }

    public boolean isPodeTrocar() {
        return podeTrocar;
    }

    public void setPodeTrocar(boolean podeTrocar) {
        this.podeTrocar = podeTrocar;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public int getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(int valorCompra) {
        this.valorCompra = valorCompra;
    }

    public int getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(int valorVenda) {
        this.valorVenda = valorVenda;
    }
}

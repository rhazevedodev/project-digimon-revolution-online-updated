package com.example.api.entity.dto;

import com.example.api.entity.Conquistas;

public class ResponseListarConquistas {

    private Conquistas conquista;

    private int idConquista;

    private String descricaoConquista;

    private String imagemConquista;

    private String tipoRecompensa;

    private int quantidadeRecompensa;

    public ResponseListarConquistas() {
    }

    public ResponseListarConquistas(Conquistas conquista, int idConquista, String descricaoConquista, String imagemConquista, String tipoRecompensa, int quantidadeRecompensa) {
        this.conquista = conquista;
        this.idConquista = idConquista;
        this.descricaoConquista = descricaoConquista;
        this.imagemConquista = imagemConquista;
        this.tipoRecompensa = tipoRecompensa;
        this.quantidadeRecompensa = quantidadeRecompensa;
    }

    public int getIdConquista() {
        return idConquista;
    }

    public void setIdConquista(int idConquista) {
        this.idConquista = idConquista;
    }

    public Conquistas getConquista() {
        return conquista;
    }

    public void setConquista(Conquistas conquista) {
        this.conquista = conquista;
    }

    public String getDescricaoConquista() {
        return descricaoConquista;
    }

    public void setDescricaoConquista(String descricaoConquista) {
        this.descricaoConquista = descricaoConquista;
    }

    public String getImagemConquista() {
        return imagemConquista;
    }

    public void setImagemConquista(String imagemConquista) {
        this.imagemConquista = imagemConquista;
    }

    public String getTipoRecompensa() {
        return tipoRecompensa;
    }

    public void setTipoRecompensa(String tipoRecompensa) {
        this.tipoRecompensa = tipoRecompensa;
    }

    public int getQuantidadeRecompensa() {
        return quantidadeRecompensa;
    }

    public void setQuantidadeRecompensa(int quantidadeRecompensa) {
        this.quantidadeRecompensa = quantidadeRecompensa;
    }
}

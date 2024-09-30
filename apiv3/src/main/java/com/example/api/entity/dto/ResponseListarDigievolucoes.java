package com.example.api.entity.dto;

public class ResponseListarDigievolucoes {

    private int idDigimonOrigem;
    private String digimonOrigem;
    private int idDigimonDestino;
    private String digimonDestino;
    private String pathImagemDigimonDestino;
    private int fragmentosNecessarios;
    private boolean itemEspecialNecessario;
    private int idItemEspecial;
    private String itemEspecial;

    public ResponseListarDigievolucoes() {
    }

    public ResponseListarDigievolucoes(int idDigimonOrigem, String digimonOrigem, int idDigimonDestino, String digimonDestino, int fragmentosNecessarios, boolean itemEspecialNecessario, String pathImagemDigimonDestino, int idItemEspecial, String itemEspecial) {
        this.idDigimonOrigem = idDigimonOrigem;
        this.digimonOrigem = digimonOrigem;
        this.idDigimonDestino = idDigimonDestino;
        this.digimonDestino = digimonDestino;
        this.fragmentosNecessarios = fragmentosNecessarios;
        this.itemEspecialNecessario = itemEspecialNecessario;
        this.pathImagemDigimonDestino = pathImagemDigimonDestino;
        this.idItemEspecial = idItemEspecial;
        this.itemEspecial = itemEspecial;
    }

    public String getPathImagemDigimonDestino() {
        return pathImagemDigimonDestino;
    }

    public void setPathImagemDigimonDestino(String pathImagemDigimonDestino) {
        this.pathImagemDigimonDestino = pathImagemDigimonDestino;
    }

    public int getIdDigimonOrigem() {
        return idDigimonOrigem;
    }

    public void setIdDigimonOrigem(int idDigimonOrigem) {
        this.idDigimonOrigem = idDigimonOrigem;
    }

    public String getDigimonOrigem() {
        return digimonOrigem;
    }

    public void setDigimonOrigem(String digimonOrigem) {
        this.digimonOrigem = digimonOrigem;
    }

    public int getIdDigimonDestino() {
        return idDigimonDestino;
    }

    public void setIdDigimonDestino(int idDigimonDestino) {
        this.idDigimonDestino = idDigimonDestino;
    }

    public String getDigimonDestino() {
        return digimonDestino;
    }

    public void setDigimonDestino(String digimonDestino) {
        this.digimonDestino = digimonDestino;
    }

    public int getFragmentosNecessarios() {
        return fragmentosNecessarios;
    }

    public void setFragmentosNecessarios(int fragmentosNecessarios) {
        this.fragmentosNecessarios = fragmentosNecessarios;
    }

    public boolean isItemEspecialNecessario() {
        return itemEspecialNecessario;
    }

    public void setItemEspecialNecessario(boolean itemEspecialNecessario) {
        this.itemEspecialNecessario = itemEspecialNecessario;
    }

    public int getIdItemEspecial() {
        return idItemEspecial;
    }

    public void setIdItemEspecial(int idItemEspecial) {
        this.idItemEspecial = idItemEspecial;
    }

    public String getItemEspecial() {
        return itemEspecial;
    }

    public void setItemEspecial(String itemEspecial) {
        this.itemEspecial = itemEspecial;
    }
}

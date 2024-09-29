package com.example.api.entity;

import jakarta.persistence.*;

@Entity
public class Digievolucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Digimon Rookie que pode evoluir
    private int digimonRookie;

    // Digimon Champion (ou outro nível) que será a evolução
    private int digimonChampion;

    private int digimonUltimate;

    private int digimonMega;

    private int fragmentosNecessarios;
    private boolean itemEspecialNecessario;
    private String itemEspecial;

    public Digievolucao() {
    }
    public Digievolucao(Long id, int digimonRookie, int digimonChampion, int digimonUltimate, int digimonMega, int fragmentosNecessarios, boolean itemEspecialNecessario, String itemEspecial) {
        this.id = id;
        this.digimonRookie = digimonRookie;
        this.digimonChampion = digimonChampion;
        this.digimonUltimate = digimonUltimate;
        this.digimonMega = digimonMega;
        this.fragmentosNecessarios = fragmentosNecessarios;
        this.itemEspecialNecessario = itemEspecialNecessario;
        this.itemEspecial = itemEspecial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDigimonRookie() {
        return digimonRookie;
    }

    public void setDigimonRookie(int digimonRookie) {
        this.digimonRookie = digimonRookie;
    }

    public int getDigimonChampion() {
        return digimonChampion;
    }

    public void setDigimonChampion(int digimonChampion) {
        this.digimonChampion = digimonChampion;
    }

    public int getDigimonUltimate() {
        return digimonUltimate;
    }

    public void setDigimonUltimate(int digimonUltimate) {
        this.digimonUltimate = digimonUltimate;
    }

    public int getDigimonMega() {
        return digimonMega;
    }

    public void setDigimonMega(int digimonMega) {
        this.digimonMega = digimonMega;
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

    public String getItemEspecial() {
        return itemEspecial;
    }

    public void setItemEspecial(String itemEspecial) {
        this.itemEspecial = itemEspecial;
    }
}

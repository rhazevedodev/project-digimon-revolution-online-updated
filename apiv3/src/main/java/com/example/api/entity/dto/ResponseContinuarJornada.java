package com.example.api.entity.dto;

import com.example.api.entity.Digimon;

import java.util.Map;

public class ResponseContinuarJornada {

    private Digimon digimon;
    private String urlImagemDigimon;

    public ResponseContinuarJornada(Digimon digimon,String urlImagemDigimon) {
        this.digimon = digimon;
        this.urlImagemDigimon = urlImagemDigimon;
    }

    public Digimon getDigimon() {
        return digimon;
    }

    public void setDigimon(Digimon digimon) {
        this.digimon = digimon;
    }

    public String getUrlImagemDigimon() {
        return urlImagemDigimon;
    }

    public void setUrlImagemDigimon(String urlImagemDigimon) {
        this.urlImagemDigimon = urlImagemDigimon;
    }
}

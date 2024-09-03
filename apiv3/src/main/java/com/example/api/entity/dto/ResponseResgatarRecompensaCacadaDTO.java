package com.example.api.entity.dto;

public class ResponseResgatarRecompensaCacadaDTO {

    private String digimon;
    private int recompensaBits;
    private int recompensaExp;
    private String recompensaFragmento;
    private int quantidadeFragmentos;

    public ResponseResgatarRecompensaCacadaDTO() {
    }

    public ResponseResgatarRecompensaCacadaDTO(String digimon, int recompensaBits, int recompensaExp, String recompensaFragmento, int quantidadeFragmentos) {
        this.digimon = digimon;
        this.recompensaBits = recompensaBits;
        this.recompensaExp = recompensaExp;
        this.recompensaFragmento = recompensaFragmento;
        this.quantidadeFragmentos = quantidadeFragmentos;
    }

    public String getDigimon() {
        return digimon;
    }

    public void setDigimon(String mensagem) {
        this.digimon = mensagem;
    }

    public int getRecompensaBits() {
        return recompensaBits;
    }

    public void setRecompensaBits(int recompensaBits) {
        this.recompensaBits = recompensaBits;
    }

    public int getRecompensaExp() {
        return recompensaExp;
    }

    public void setRecompensaExp(int recompensaExp) {
        this.recompensaExp = recompensaExp;
    }

    public String getRecompensaFragmento() {
        return recompensaFragmento;
    }

    public void setRecompensaFragmento(String recompensaFragmento) {
        this.recompensaFragmento = recompensaFragmento;
    }

    public int getQuantidadeFragmentos() {
        return quantidadeFragmentos;
    }

    public void setQuantidadeFragmentos(int quantidadeFragmentos) {
        this.quantidadeFragmentos = quantidadeFragmentos;
    }
}

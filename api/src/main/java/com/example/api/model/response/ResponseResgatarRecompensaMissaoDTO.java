package com.example.api.model.response;

public class ResponseResgatarRecompensaMissaoDTO {

    private String digimon;
    private int recompensaBits;
    private String recompensaFragmento;
    private int quantidadeFragmentos;

    public ResponseResgatarRecompensaMissaoDTO() {
    }

    public ResponseResgatarRecompensaMissaoDTO(String digimon, int recompensaBits, String recompensaFragmento, int quantidadeFragmentos) {
        this.digimon = digimon;
        this.recompensaBits = recompensaBits;
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

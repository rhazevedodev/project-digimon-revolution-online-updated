package com.example.api.model.request;

public class RequestSalvarAtributos {

    private Long idDigimon;
    private int custoTotal;
    private int forca;
    private int inteligencia;
    private int conhecimento;
    private int agilidade;

    public RequestSalvarAtributos() {
    }

    public RequestSalvarAtributos(Long idDigimon, int custoTotal, int forca, int inteligencia, int conhecimento, int agilidade) {
        this.idDigimon = idDigimon;
        this.custoTotal = custoTotal;
        this.forca = forca;
        this.inteligencia = inteligencia;
        this.conhecimento = conhecimento;
        this.agilidade = agilidade;
    }

    public Long getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(Long idDigimon) {
        this.idDigimon = idDigimon;
    }

    public int getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(int custoTotal) {
        this.custoTotal = custoTotal;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getConhecimento() {
        return conhecimento;
    }

    public void setConhecimento(int conhecimento) {
        this.conhecimento = conhecimento;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }
}

package com.example.api.entity.dto;

public class RequestDigievolucao {

    private int idDigimon;
    private String evolucaoEscolhida;
    private int fragmentosNecessarios;

    public RequestDigievolucao() {
    }

    public RequestDigievolucao(int idDigimon, String evolucaoEscolhida, int FragmentosNecessarios) {
        this.idDigimon = idDigimon;
        this.evolucaoEscolhida = evolucaoEscolhida;

    }

    public int getFragmentosNecessarios() {
        return fragmentosNecessarios;
    }

    public void setFragmentosNecessarios(int fragmentosNecessarios) {
        this.fragmentosNecessarios = fragmentosNecessarios;
    }

    public int getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(int idDigimon) {
        this.idDigimon = idDigimon;
    }

    public String getEvolucaoEscolhida() {
        return evolucaoEscolhida;
    }

    public void setEvolucaoEscolhida(String evolucaoEscolhida) {
        this.evolucaoEscolhida = evolucaoEscolhida;
    }
}

package com.example.api.model;

import com.example.api.model.entity.Digimon;

public class ResultadoCombateInvasao {

    private String log;
    private Digimon winner;
    private Digimon loser;
    private int totalDanoAtacante;
    private int totalDanoDefensor;
    private int vidaRestanteAtacante;
    private int vidaRestanteDefensor;

    public ResultadoCombateInvasao() {
    }

    public ResultadoCombateInvasao(String log, Digimon winner, Digimon loser, int totalDanoAtacante, int totalDanoDefensor, int vidaRestanteAtacante, int vidaRestanteDefensor) {
        this.log = log;
        this.winner = winner;
        this.loser = loser;
        this.totalDanoAtacante = totalDanoAtacante;
        this.totalDanoDefensor = totalDanoDefensor;
        this.vidaRestanteAtacante = vidaRestanteAtacante;
        this.vidaRestanteDefensor = vidaRestanteDefensor;
    }

    public int getTotalDanoAtacante() {
        return totalDanoAtacante;
    }

    public void setTotalDanoAtacante(int totalDanoAtacante) {
        this.totalDanoAtacante = totalDanoAtacante;
    }

    public int getTotalDanoDefensor() {
        return totalDanoDefensor;
    }

    public void setTotalDanoDefensor(int totalDanoDefensor) {
        this.totalDanoDefensor = totalDanoDefensor;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Digimon getWinner() {
        return winner;
    }

    public void setWinner(Digimon winner) {
        this.winner = winner;
    }

    public Digimon getLoser() {
        return loser;
    }

    public void setLoser(Digimon loser) {
        this.loser = loser;
    }

    public int getVidaRestanteAtacante() {
        return vidaRestanteAtacante;
    }

    public void setVidaRestanteAtacante(int vidaRestanteAtacante) {
        this.vidaRestanteAtacante = vidaRestanteAtacante;
    }

    public int getVidaRestanteDefensor() {
        return vidaRestanteDefensor;
    }

    public void setVidaRestanteDefensor(int vidaRestanteDefensor) {
        this.vidaRestanteDefensor = vidaRestanteDefensor;
    }
}

package com.example.api.enumerator;

public enum EnumMissoes {

    MISSAO1("1", "1", "250", "1"),
    MISSAO2("2", "1", "500", "10"),
    MISSAO3("3", "1", "1000", "20"),
    MISSAO4("4", "1", "2000", "30"),
    MISSAO5("5", "1", "4000", "40"),
    MISSAO6("6", "1", "6000", "50");

    private final String id;
    private final String tier;
    private final String bitsHora;
    private final String nivelMinimo;

    EnumMissoes(String id, String tier, String bitsHora, String nivelMinimo) {
        this.id = id;
        this.tier = tier;
        this.bitsHora = bitsHora;
        this.nivelMinimo = nivelMinimo;
    }

    public String getNivelMinimo() {
        return nivelMinimo;
    }

    public String getId() {
        return id;
    }

    public String getTier() {
        return tier;
    }

    public String getBitsHora() {
        return bitsHora;
    }

    public static String getNvMinimoByIdMissao(int idMissao) {
        for (EnumMissoes missao : EnumMissoes.values()) {
            if (missao.getId().equals(String.valueOf(idMissao))) {
                return missao.getNivelMinimo();
            }
        }
        throw new RuntimeException("ID de Missão inválido: " + idMissao);
    }
}


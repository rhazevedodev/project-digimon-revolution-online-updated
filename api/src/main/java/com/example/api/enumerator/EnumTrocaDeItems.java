package com.example.api.enumerator;

public enum EnumTrocaDeItems {

    BAU_FRAGMENTOS_CHAMPION("1", "CHAMPION",5000, 10, "Baú contendo de 5 a 10 fragmentos aleatórios de um Digimon Champion"),
    BAU_FRAGMENTOS_ULTIMATE("2", "ULTIMATE",10000, 10, "Baú contendo de 5 a 10 fragmentos aleatórios de um Digimon Ultimate"),
    BAU_FRAGMENTOS_MEGA("3", "MEGA",25000, 10, "Baú contendo de 5 a 10 fragmentos aleatórios de um Digimon Mega");

    private final String id;
    private final String tier;
    private final int custo;
    private final int quantidadeMinimaParaTroca;
    private final String descricaoItem;

    EnumTrocaDeItems(String id, String tier, int custo, int quantidadeMinimaParaTroca, String descricaoItem) {
        this.id = id;
        this.tier = tier;
        this.custo = custo;
        this.quantidadeMinimaParaTroca = quantidadeMinimaParaTroca;
        this.descricaoItem = descricaoItem;
    }

    public String getTier() {
        return tier;
    }

    public int getCusto() {
        return custo;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public String getId() {
        return id;
    }

    public int getQuantidadeMinimaParaTroca() {
        return quantidadeMinimaParaTroca;
    }

    public static int getCustoByTier(String tier){
        for (EnumTrocaDeItems enumTrocaDeItems : values()) {
            if (enumTrocaDeItems.getTier().equalsIgnoreCase(tier)) {
                return enumTrocaDeItems.getCusto();
            }
        }
        throw new IllegalArgumentException("Tier não encontrado: " + tier);
    }

    public static int getQuantidadeMinimaParaTrocaByTier(String tier){
        for (EnumTrocaDeItems enumTrocaDeItems : values()) {
            if (enumTrocaDeItems.getTier().equalsIgnoreCase(tier)) {
                return enumTrocaDeItems.getQuantidadeMinimaParaTroca();
            }
        }
        throw new IllegalArgumentException("Tier não encontrado: " + tier);
    }
}

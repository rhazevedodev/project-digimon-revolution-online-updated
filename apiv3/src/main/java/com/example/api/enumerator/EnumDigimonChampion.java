package com.example.api.enumerator;


import com.example.api.service.LogService;

public enum EnumDigimonChampion {

    GREYMON(1, "GREYMON", "FOGO", "./imagens/digimons/champions/greymon2.jpg"),
    GARURUMON(2, "GARURUMON", "GELO", "./imagens/digimons/champions/garurumon.jpg"),
    BIRDRAMON(3, "BIRDRAMON", "VENTO", "./imagens/digimons/champions/birdramon.jpg"),
    KABUTERIMON(4, "KABUTERIMON", "RAIO", "./imagens/digimons/champions/kabuterimon.jpg"),
    TOGEMON(5, "TOGEMON", "PLANTA", "./imagens/digimons/champions/togemon.jpg"),
    IKKAKUMON(6, "IKKAKUMON", "ÁGUA", "./imagens/digimons/champions/ikkakumon.jpg"),
    ANGEMON(7, "ANGEMON", "LUZ", "./imagens/digimons/champions/angemon.jpg"),
    TAILMON(8, "TAILMON", "LUZ", "./imagens/digimons/champions/tailmon.jpg"),
    EXVEEMON(9, "EXVEEMON", "FOGO", "./imagens/digimons/champions/exveemon.jpg"),
    STINGMON(10, "STINGMON", "TERRA", "./imagens/digimons/champions/stingmon.jpg");

    private int id;
    private String descricao;
    private String elemento;
    private String urlImg;

    private static LogService logService;

    EnumDigimonChampion(int id, String descricao, String elemento, String urlImg) {
        this.id = id;
        this.descricao = descricao;
        this.elemento = elemento;
        this.urlImg = urlImg;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getElemento() {
        return elemento;
    }

    // Método estático para obter EnumDigimonRookie pelo ID
    public static EnumDigimonChampion getEnumById(int id) {
        for (EnumDigimonChampion digimon : EnumDigimonChampion.values()) {
            if (digimon.getId() == id) {
                return digimon;
            }
        }
        logService.logAction("Erro ao obter Digimon Champion", "ID de Digimon Champion inválido: " + id);
        throw new RuntimeException("ID de Digimon Champion inválido: " + id);
    }

    public static String getDescricaoById(int id) {
        for (EnumDigimonChampion digimon : EnumDigimonChampion.values()) {
            if (digimon.getId() == id) {
                return digimon.getDescricao();
            }
        }
        logService.logAction("Erro ao obter Descrição de Digimon Champion", "ID de Digimon Champion inválido: " + id);
        throw new RuntimeException("ID de Digimon Champion inválido: " + id);
    }

    public static void setLogService(LogService logService) {
        EnumDigimonChampion.logService = logService;
    }
}

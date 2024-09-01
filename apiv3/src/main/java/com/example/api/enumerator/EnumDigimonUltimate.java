package com.example.api.enumerator;


import com.example.api.service.LogService;

public enum EnumDigimonUltimate {

    METALGREYMON(1, "METALGREYMON", "FOGO", "./imagens/digimons/champions/metalgreymon.jpg"),
    WEREGARURUMON(2, "WEREGARURUMON", "GELO", "./imagens/digimons/rookies/weregarurumon.jpg"),
    GARUDAMON(3, "GARUDAMON", "VENTO", "./imagens/digimons/rookies/birdramon.jpg"),
    MEGAKABUTERIMON(4, "MEGAKABUTERIMON", "METAL", "./imagens/digimons/rookies/kabuterimon.jpg"),
    LILLIMON(5, "LILLIMON", "PLANTA", "./imagens/digimons/rookies/togemon.jpg"),
    ZUDOMON(6, "ZUDOMON", "ÁGUA", "./imagens/digimons/rookies/ikkakumon.jpg"),
    MAGNAANGEMON(7, "MAGNAANGEMON", "LUZ", "./imagens/digimons/rookies/angemon.jpg");

    private int id;
    private String descricao;
    private String elemento;
    private String urlImg;

    private static LogService logService;

    EnumDigimonUltimate(int id, String descricao, String elemento, String urlImg) {
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
    public static EnumDigimonUltimate getEnumById(int id) {
        for (EnumDigimonUltimate digimon : EnumDigimonUltimate.values()) {
            if (digimon.getId() == id) {
                return digimon;
            }
        }
        logService.logAction("Erro ao obter Digimon Ultimate", "ID de Digimon Ultimate inválido: " + id);
        throw new RuntimeException("ID de Digimon Ultimate inválido: " + id);
    }

    public static String getDescricaoById(int id) {
        for (EnumDigimonUltimate digimon : EnumDigimonUltimate.values()) {
            if (digimon.getId() == id) {
                return digimon.getDescricao();
            }
        }
        logService.logAction("Erro ao obter Descrição de Digimon Ultimate", "ID de Digimon Ultimate inválido: " + id);
        throw new RuntimeException("ID de Digimon Ultimate inválido: " + id);
    }

    public static void setLogService(LogService logService) {
        EnumDigimonUltimate.logService = logService;
    }
}

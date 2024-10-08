package com.example.api.enumerator;


import com.example.api.service.LogService;

public enum EnumDigimonUltimate {

    METALGREYMON(1, "MetalGreymon", "FOGO", "./imagens/digimons/ultimates/metalgreymon.jpg"),
    WEREGARURUMON(2, "WereGarurumon", "GELO", "./imagens/digimons/ultimates/weregarurumon.jpg"),
    GARUDAMON(3, "Garudamon", "VENTO", "./imagens/digimons/ultimates/garudamon.jpg"),
    MEGAKABUTERIMON(4, "Megakabuterimon", "METAL", "./imagens/digimons/ultimates/megakabuterimon.jpg"),
    LILLIMON(5, "Lillimon", "PLANTA", "./imagens/digimons/ultimates/lillimon.jpg"),
    ZUDOMON(6, "Zudomon", "ÁGUA", "./imagens/digimons/ultimates/zudomon.jpg"),
    MAGNAANGEMON(7, "Magnaangemon", "LUZ", "./imagens/digimons/ultimates/magnaangemon.jpg");

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

    public static String getUrlImgById(int idUltimate) {
        for (EnumDigimonUltimate digimon : EnumDigimonUltimate.values()) {
            if (digimon.getId() == idUltimate) {
                return digimon.getUrlImg();
            }
        }
        logService.logAction("Erro ao obter URL de Imagem de Digimon Ultimate", "ID de Digimon Ultimate inválido: " + idUltimate);
        throw new RuntimeException("ID de Digimon Ultimate inválido: " + idUltimate);
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

    public static int getIdByDescricao(String descricao) {
        for (EnumDigimonUltimate digimon : EnumDigimonUltimate.values()) {
            if (digimon.getDescricao().equals(descricao)) {
                return digimon.getId();
            }
        }
        logService.logAction("Erro ao obter ID de Digimon Ultimate", "Descrição de Digimon Ultimate inválida: " + descricao);
        throw new RuntimeException("Descrição de Digimon Ultimate inválida: " + descricao);
    }

    public static void setLogService(LogService logService) {
        EnumDigimonUltimate.logService = logService;
    }
}

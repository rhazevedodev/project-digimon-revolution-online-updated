package com.example.api.enumerator;


import com.example.api.service.LogService;

public enum EnumDigimonRookie {

    AGUMON(1, "Agumon", "FOGO", "/imagens/digimons/rookies/agumon.jpg"),
    GABUMON(2, "Gabumon", "FOGO", "/imagens/digimons/rookies/gabumon.jpg"),
    PIYOMON(3, "Piyomon", "VENTO", "/imagens/digimons/rookies/piyomon.jpg"),
    TENTOMON(4, "Tentomon", "RAIO", "/imagens/digimons/rookies/tentomon.jpg"),
    PALMON(5, "Palmon", "PLANTA", "/imagens/digimons/rookies/palmon.jpg"),
    GOMAMON(6, "Gomamon", "AGUA", "/imagens/digimons/rookies/gomamon.jpg"),
    PATAMON(7, "Patamon", "LUZ", "/imagens/digimons/rookies/patamon.jpg"),
    SALAMON(8, "Salamon", "LUZ", "/imagens/digimons/rookies/salamon.jpg"),
    VEEMON(9, "Veemon", "FOGO", "/imagens/digimons/rookies/veemon.jpg"),
    WORMMON(10, "Wormmon", "TREVAS", "/imagens/digimons/rookies/wormmon.jpg"),
    BEARMON(11, "Bearmon", "TERRA", "/imagens/digimons/rookies/bearmon.jpg"),
    GUILMON(12, "Guilmon", "FOGO", "/imagens/digimons/rookies/guilmon.jpg"),
    TERRIERMON(13, "Terriermon", "VENTO", "/imagens/digimons/rookies/terriermon.jpg"),
    RENAMON(14, "Renamon", "VENTO", "/imagens/digimons/rookies/renamon.jpg"),
    HAWKMON(15, "Hawkmon", "VENTO", "/imagens/digimons/rookies/hawkmon.jpg"),
    ARMADILLOMON(16, "Armadillomon", "TERRA", "/imagens/digimons/rookies/armadillomon.jpg"),
    IMPMON(17, "Impmon", "TREVAS", "/imagens/digimons/rookies/impmon.jpg"),
    DEVIDEVIMON(18, "Devidevimon", "TREVAS", "/imagens/digimons/rookies/devidevimon.jpg"),
    MONODRAMON(19, "Monodramon", "TREVAS", "/imagens/digimons/rookies/monodramon.jpg"),
    LOPMON(20, "Lopmon", "GELO", "/imagens/digimons/rookies/lopmon.jpg"),
    BETAMON(21, "Betamon", "PLANTA", "/imagens/digimons/rookies/betamon.jpg");

    private int id;
    private String descricao;
    private String elemento;
    private String urlImg;

    private static LogService logService;

    EnumDigimonRookie(int id, String descricao, String elemento, String urlImg) {
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
    public static EnumDigimonRookie getEnumById(int id) {
        for (EnumDigimonRookie digimon : EnumDigimonRookie.values()) {
            if (digimon.getId() == id) {
                return digimon;
            }
        }
        logService.logAction("Erro ao obter Digimon Rookie", "ID de Digimon Rookie inválido: " + id);
        throw new RuntimeException("ID de Digimon Rookie inválido: " + id);
    }

    public static String getDescricaoById(int id) {
        for (EnumDigimonRookie digimon : EnumDigimonRookie.values()) {
            if (digimon.getId() == id) {
                return digimon.getDescricao();
            }
        }
        logService.logAction("Erro ao obter Descrição de Digimon Rookie", "ID de Digimon Rookie inválido: " + id);
        throw new RuntimeException("ID de Digimon Rookie inválido: " + id);
    }

    public static String getUrlImgById(int id) {
        for (EnumDigimonRookie digimon : EnumDigimonRookie.values()) {
            if (digimon.getId() == id) {
                return digimon.getUrlImg();
            }
        }
        logService.logAction("Erro ao obter UrlImg de Digimon Rookie", "ID de Digimon Rookie inválido: " + id);
        throw new RuntimeException("ID de Digimon Rookie inválido: " + id);
    }

    public static String getIdByDescricao(String descricao) {
        for (EnumDigimonRookie digimon : EnumDigimonRookie.values()) {
            if (digimon.getDescricao().equals(descricao)) {
                return String.valueOf(digimon.getId());
            }
        }
        logService.logAction("Erro ao obter ID de Digimon Rookie", "Descrição de Digimon Rookie inválida: " + descricao);
        throw new RuntimeException("Descrição de Digimon Rookie inválida: " + descricao);
    }

    public static String getElementoByEnum(EnumDigimonRookie digimon) {
        return digimon.getElemento();
    }

    public static void setLogService(LogService logService) {
        EnumDigimonRookie.logService = logService;
    }
}

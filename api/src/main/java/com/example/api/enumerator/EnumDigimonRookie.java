package com.example.api.enumerator;


import com.example.api.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;

public enum EnumDigimonRookie {

    AGUMON(1, "Agumon", "FOGO", "/frontend/images/rookies/agumon.jpg"),
    GABUMON(2, "Gabumon", "FOGO", "/frontend/images/rookies/gabumon.jpg"),
    PIYOMON(3, "Piyomon", "VENTO", "/frontend/images/rookies/piyomon.jpg"),
    TENTOMON(4, "Tentomon", "RAIO", "/frontend/images/rookies/tentomon.jpg"),
    PALMON(5, "Palmon", "PLANTA", "/frontend/images/rookies/palmon.jpg"),
    GOMAMON(6, "Gomamon", "AGUA", "/frontend/images/rookies/gomamon.jpg"),
    PATAMON(7, "Patamon", "LUZ", "/frontend/images/rookies/patamon.jpg"),
    SALAMON(8, "Salamon", "LUZ", "/frontend/images/rookies/salamon.jpg"),
    VEEMON(9, "Veemon", "FOGO", "/frontend/images/rookies/veemon.jpg"),
    WORMMON(10, "Wormmon", "TREVAS", "/frontend/images/rookies/wormmon.jpg"),
    BEARMON(11, "Bearmon", "TERRA", "/frontend/images/rookies/bearmon.jpg"),
    GUILMON(12, "Guilmon", "FOGO", "/frontend/images/rookies/guilmon.jpg"),
    TERRIERMON(13, "Terriermon", "VENTO", "/frontend/images/rookies/terriermon.jpg"),
    RENAMON(14, "Renamon", "VENTO", "/frontend/images/rookies/renamon.jpg"),
    HAWKMON(15, "Hawkmon", "VENTO", "/frontend/images/rookies/hawkmon.jpg"),
    ARMADILLOMON(16, "Armadillomon", "TERRA", "/frontend/images/rookies/armadillomon.jpg"),
    IMPMON(17, "Impmon", "TREVAS", "/frontend/images/rookies/impmon.jpg"),
    DEVIDEVIMON(18, "Devidevimon", "TREVAS", "/frontend/images/rookies/devidevimon.jpg"),
    MONODRAMON(19, "Monodramon", "TREVAS", "/frontend/images/rookies/monodramon.jpg"),
    LOPMON(20, "Lopmon", "GELO", "/frontend/images/rookies/lopmon.jpg"),
    BETAMON(21, "Betamon", "PLANTA", "/frontend/images/rookies/betamon.jpg");

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

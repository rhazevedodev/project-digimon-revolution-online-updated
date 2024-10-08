package com.example.api.enumerator;


import com.example.api.service.LogService;

public enum EnumDigimonMega {

    WARGREYMON(1, "Wargreymon", "FOGO", "./imagens/digimons/megas/wargreymon.jpg"),
    METALGARURUMON(2, "Metalgarurumon", "GELO", "./imagens/digimons/megas/metalgarurumon.jpg"),
    PHOENIXMON(3, "Phoenixmon", "VENTO", "./imagens/digimons/megas/phoenixmon.jpg"),
    HERCULESKABUTERIMON(4, "Herculeskabuterimon", "METAL", "./imagens/digimons/megas/herculeskabuterimon.jpg"),
    ROSEMON(5, "Rosemon", "PLANTA", "./imagens/digimons/megas/rosemon.jpg"),
    VIKEMON(6, "Vikemon", "ÁGUA", "./imagens/digimons/megas/vikemon.jpg"),
    SERAPHIMON(7, "Seraphimon", "LUZ", "./imagens/digimons/megas/seraphimon.jpg");

    private int id;
    private String descricao;
    private String elemento;
    private String urlImg;

    private static LogService logService;

    EnumDigimonMega(int id, String descricao, String elemento, String urlImg) {
        this.id = id;
        this.descricao = descricao;
        this.elemento = elemento;
        this.urlImg = urlImg;
    }

    public static String getUrlImgById(int idMega) {
        for (EnumDigimonMega digimon : EnumDigimonMega.values()) {
            if (digimon.getId() == idMega) {
                return digimon.getUrlImg();
            }
        }
        logService.logAction("Erro ao obter URL de Imagem de Digimon Mega", "ID de Digimon Mega inválido: " + idMega);
        throw new RuntimeException("ID de Digimon Mega inválido: " + idMega);
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
    public static EnumDigimonMega getEnumById(int id) {
        for (EnumDigimonMega digimon : EnumDigimonMega.values()) {
            if (digimon.getId() == id) {
                return digimon;
            }
        }
        logService.logAction("Erro ao obter Digimon Mega", "ID de Digimon Mega inválido: " + id);
        throw new RuntimeException("ID de Digimon Mega inválido: " + id);
    }

    public static String getDescricaoById(int id) {
        for (EnumDigimonMega digimon : EnumDigimonMega.values()) {
            if (digimon.getId() == id) {
                return digimon.getDescricao();
            }
        }
        logService.logAction("Erro ao obter Descrição de Digimon Mega", "ID de Digimon Mega inválido: " + id);
        throw new RuntimeException("ID de Digimon Mega inválido: " + id);
    }

    public static int getIdByDescricao(String descricao) {
        for (EnumDigimonMega digimon : EnumDigimonMega.values()) {
            if (digimon.getDescricao().equals(descricao)) {
                return digimon.getId();
            }
        }
        logService.logAction("Erro ao obter ID de Digimon Mega", "Descrição de Digimon Mega inválida: " + descricao);
        throw new RuntimeException("Descrição de Digimon Mega inválida: " + descricao);
    }

    public static void setLogService(LogService logService) {
        EnumDigimonMega.logService = logService;
    }
}

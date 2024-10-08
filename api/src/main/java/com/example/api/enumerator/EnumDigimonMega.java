package com.example.api.enumerator;


import com.example.api.service.LogService;

public enum EnumDigimonMega {

    WARGREYMON(1, "WARGREYMON", "FOGO", "./imagens/digimons/champions/metalgreymon.jpg"),
    METALGARURUMON(2, "METALGARURUMON", "GELO", "./imagens/digimons/rookies/weregarurumon.jpg"),
    PHOENIXMON(3, "PHOENIXMON", "VENTO", "./imagens/digimons/rookies/birdramon.jpg"),
    HERCULESKABUTERIMON(4, "HERCULESKABUTERIMON", "METAL", "./imagens/digimons/rookies/kabuterimon.jpg"),
    ROSEMON(5, "ROSEMON", "PLANTA", "./imagens/digimons/rookies/togemon.jpg"),
    VIKEMON(6, "VIKEMON", "ÁGUA", "./imagens/digimons/rookies/ikkakumon.jpg"),
    SERAPHIMON(7, "SERAPHIMON", "LUZ", "./imagens/digimons/rookies/angemon.jpg");

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

    public static void setLogService(LogService logService) {
        EnumDigimonMega.logService = logService;
    }
}

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
    STINGMON(10, "STINGMON", "TERRA", "./imagens/digimons/champions/stingmon.jpg"),
    GATOMON(11, "GATOMON", "LUZ", "./imagens/digimons/champions/gatomon.jpg"),
    DEVIMON(12, "DEVIMON", "TREVAS", "./imagens/digimons/champions/devimon.jpg"),
    KYUBIMON(13, "KYUBIMON", "FOGO", "./imagens/digimons/champions/kyubimon.jpg"),
    GARGOMON(14, "GARGOMON", "TERRA", "./imagens/digimons/champions/gargomon.jpg"),
    AQUILAMON(15, "AQUILAMON", "VENTO", "./imagens/digimons/champions/aquilamon.jpg"),
    ANKYLOMON(16, "ANKYLOMON", "TERRA", "./imagens/digimons/champions/ankylomon.jpg"),
    GROWLMON(17, "GROWLMON", "TERRA", "./imagens/digimons/champions/growlmon.jpg"),
    ANTYLAMON(18, "ANTYLAMON", "LUZ", "./imagens/digimons/champions/antylamon.jpg"),
    HOOKMON(19, "HOOKMON", "TERRA", "./imagens/digimons/champions/hookmon.jpg"),
    GRIZZLYMON(20, "GRIZZLYMON", "TERRA", "./imagens/digimons/champions/grizzlymon.jpg"),
    DINOREXMON(21, "DINOREXMON", "TERRA", "./imagens/digimons/champions/dinorexmon.jpg"),
    STRIKEDRAMON(22, "STRIKEDRAMON", "TERRA", "./imagens/digimons/champions/strikedramon.jpg"),
    MYOTISMON(23, "MYOTISMON", "TREVAS", "./imagens/digimons/champions/myotismon.jpg"),
    MEGASEADRAMON(24, "MEGASEADRAMON", "ÁGUA", "./imagens/digimons/champions/megaseadramon.jpg"),
    SKULLGREYMON(25, "SKULLGREYMON", "TREVAS", "./imagens/digimons/champions/skullgreymon.jpg"),
    RISEGREYMON(26, "RISEGREYMON", "FOGO", "./imagens/digimons/champions/risegreymon.jpg"),
    BOLTMON(27, "BOLTMON", "METAL", "./imagens/digimons/champions/boltmon.jpg"),
    DUSKMON(28, "DUSKMON", "TREVAS", "./imagens/digimons/champions/duskmon.jpg");




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

    public static String getUrlImgById(int idChampion) {
        for (EnumDigimonChampion digimon : EnumDigimonChampion.values()) {
            if (digimon.getId() == idChampion) {
                return digimon.getUrlImg();
            }
        }
        logService.logAction("Erro ao obter URL de Imagem de Digimon Champion", "ID de Digimon Champion inválido: " + idChampion);
        throw new RuntimeException("ID de Digimon Champion inválido: " + idChampion);
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

    public static int getIdByDescricao(String descricao) {
        for (EnumDigimonChampion digimon : EnumDigimonChampion.values()) {
            if (digimon.getDescricao().equalsIgnoreCase(descricao)) {
                return digimon.getId();
            }
        }
        logService.logAction("Erro ao obter ID de Digimon Champion", "Descrição de Digimon Champion inválida: " + descricao);
        throw new RuntimeException("Descrição de Digimon Champion inválida: " + descricao);
    }

    public static void setLogService(LogService logService) {
        EnumDigimonChampion.logService = logService;
    }
}

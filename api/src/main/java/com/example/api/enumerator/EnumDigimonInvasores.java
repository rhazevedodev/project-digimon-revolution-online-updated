package com.example.api.enumerator;

public enum EnumDigimonInvasores {

    IMPERIALDRAMON_PALADIN_MODE(1, "Imperialdramon Paladin Mode", 100000, 100, 10000,"/imagens/digimons/invasores/digimon_imperialdramon_paladinmode.jpg"),
    LUCEMON(2, "Lucemon", 100000, 100, 10000,"/imagens/digimons/invasores/digimon_lucemon.jpg"),
    LUCEMON_FALLEN_MODE(3, "Lucemon Fallen Mode", 100000, 100, 10000,"/imagens/digimons/invasores/digimon_lucemon_falldawn_mode.jpg"),
    OMEGAMON(4, "Omegamon", 100000, 100, 10000,"/imagens/digimons/invasores/digimon_omegamon.jpg"),
    DUKEMON_CRIMSON_MODE(5, "Dukemon Crimson Mode", 100000, 100, 10000,"/imagens/digimons/invasores/digimon_dukemon_crimson_mode.jpg");

    private int id;
    private String nome;
    private int recompensaBits;
    private int recompensaDiamantes;
    private int energiaVital;
    private String urlImg;

    EnumDigimonInvasores(int id, String nome, int recompensaBits, int recompensaDiamantes, int energiaVital, String urlImg) {
        this.id = id;
        this.nome = nome;
        this.recompensaBits = recompensaBits;
        this.recompensaDiamantes = recompensaDiamantes;
        this.energiaVital = energiaVital;
        this.urlImg = urlImg;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getRecompensaBits() {
        return recompensaBits;
    }

    public int getRecompensaDiamantes() {
        return recompensaDiamantes;
    }

    public int getEnergiaVital() {
        return energiaVital;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public static String getNomeById(int id) {
        for (EnumDigimonInvasores digimon : EnumDigimonInvasores.values()) {
            if (digimon.getId() == id) {
                return digimon.getNome();
            }
        }
        throw new RuntimeException("ID de Digimon Invasor inválido: " + id);
    }

    public static int getEnergiaVitalById(int id) {
        for (EnumDigimonInvasores digimon : EnumDigimonInvasores.values()) {
            if (digimon.getId() == id) {
                return digimon.getEnergiaVital();
            }
        }
        throw new RuntimeException("ID de Digimon Invasor inválido: " + id);
    }

    public static String getUrlImgById(int id) {
        for (EnumDigimonInvasores digimon : EnumDigimonInvasores.values()) {
            if (digimon.getId() == id) {
                return digimon.getUrlImg();
            }
        }
        throw new RuntimeException("ID de Digimon Invasor inválido: " + id);
    }

    public static int getRecompensaBitsById(int id) {
        for (EnumDigimonInvasores digimon : EnumDigimonInvasores.values()) {
            if (digimon.getId() == id) {
                return digimon.getRecompensaBits();
            }
        }
        throw new RuntimeException("ID de Digimon Invasor inválido: " + id);
    }

    public static int getRecompensaDiamantesById(int id) {
        for (EnumDigimonInvasores digimon : EnumDigimonInvasores.values()) {
            if (digimon.getId() == id) {
                return digimon.getRecompensaDiamantes();
            }
        }
        throw new RuntimeException("ID de Digimon Invasor inválido: " + id);
    }




}

package com.example.api.enumerator;

public enum EnumElementos {

    FOGO("1", "FOGO"),
    AGUA("2", "AGUA"),
    TERRA("3", "TERRA"),
    VENTO("4", "VENTO"),
    LUZ("5", "LUZ"),
    TREVAS("6", "TREVAS"),
    RAIO("7", "RAIO"),
    GELO("8", "GELO"),
    METAL("9", "METAL");

    private final String id;
    private final String nomeElemento;

    EnumElementos(String id, String nomeElemento) {
        this.id = id;
        this.nomeElemento = nomeElemento;
    }

    public String getId() {
        return id;
    }

    public String getNomeElemento() {
        return nomeElemento;
    }
}


package com.example.api.enumerator;

public enum EnumCategoriasInventario {

    ITENS(1, "ITENS"),
    EQUIPAMENTOS(2, "EQUIPAMENTOS"),
    CONSUMIVEIS(3, "CONSUMIVEIS"),
    FRAGMENTOS(4, "FRAGMENTOS");

    private final int id;
    private final String nomeCategoria;

    EnumCategoriasInventario(int id, String nomeCategoria) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
    }

    public int getId() {
        return id;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public static String getNomeCategoriaById(int id) {
        for (EnumCategoriasInventario inventario : EnumCategoriasInventario.values()) {
            if (inventario.getId() == id) {
                return inventario.getNomeCategoria();
            }
        }
        throw new RuntimeException("ID de categoria  inválida: " + id);
    }
}


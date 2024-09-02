package com.example.api.enumerator;

public enum EnumItens {

    POCAO_CURA_1(1, "POCAO DE CURA 1", "Recupera 15% dos seus pontos de vida", "consumivel", 100, false, false),
    POCAO_CURA_2(2, "POCAO DE CURA 2", "Recupera 35% dos seus pontos de vida", "consumivel", 350, false, false),
    POCAO_CURA_3(3, "POCAO DE CURA 3", "Recupera 55% dos seus pontos de vida", "consumivel", 550, false, false);


    private final int id;
    private final String nome;
    private final String descricao;
    private final String categoria;
    private final int valorCompra;
    private boolean podeVender;
    private boolean podeTrocar;


    EnumItens(int id, String nome, String descricao, String categoria, int valorCompra, boolean podeVender, boolean podeTrocar) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valorCompra = valorCompra;
        this.podeVender = podeVender;
        this.podeTrocar = podeTrocar;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getValorCompra() {
        return valorCompra;
    }

    public boolean isPodeVender() {
        return podeVender;
    }

    public boolean isPodeTrocar() {
        return podeTrocar;
    }
}


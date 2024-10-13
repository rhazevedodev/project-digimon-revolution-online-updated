package com.example.api.enumerator;

public enum EnumConquistas {

    CONQUISTA1(1, "Caçador I", "Complete sua primeira caçada", "exploracao.jpg", "DIAMANTE",10),
    CONQUISTA2(2, "Explorador I", "Complete sua primeira  missao", "exploracao.jpg", "DIAMANTE",10),
    CONQUISTA3(3, "Em busca da riqueza I", "Acumule 100 bits", "exploracao.jpg", "DIAMANTE",10),
    CONQUISTA4(4, "Aprendiz de treinador I", "Treine seu digimon pela primeira vez", "exploracao.jpg", "DIAMANTE",10);

        private final int id;
        private final String nomeConquista;
        private final String descricaoConquista;
        private final String imagemConquista;
        private final String tipoRecompensa;
        private final int quantidadeRecompensa;

    EnumConquistas(int id, String nomeConquista, String descricaoConquista, String imagemConquista, String tipoRecompensa, int quantidadeRecompensa) {
        this.id = id;
        this.nomeConquista = nomeConquista;
        this.descricaoConquista = descricaoConquista;
        this.imagemConquista = imagemConquista;
        this.tipoRecompensa = tipoRecompensa;
        this.quantidadeRecompensa = quantidadeRecompensa;
    }

    public static int getIdConquistaByNomeConquista(String nomeConquista) {
        for (EnumConquistas conquista : EnumConquistas.values()) {
            if (conquista.getNomeConquista().equals(nomeConquista)) {
                return conquista.getId();
            }
        }
        return 0;
    }

    public static String getDescricaoConquistaByNomeConquista(String nomeConquista) {
        for (EnumConquistas conquista : EnumConquistas.values()) {
            if (conquista.getNomeConquista().equals(nomeConquista)) {
                return conquista.getDescricaoConquista();
            }
        }
        return "";
    }

    public static String getImagemConquistaByNomeConquista(String nomeConquista) {
        for (EnumConquistas conquista : EnumConquistas.values()) {
            if (conquista.getNomeConquista().equals(nomeConquista)) {
                return conquista.getImagemConquista();
            }
        }
        return "";
    }

    public static String getTipoRecompensaByNomeConquista(String nomeConquista) {
        for (EnumConquistas conquista : EnumConquistas.values()) {
            if (conquista.getNomeConquista().equals(nomeConquista)) {
                return conquista.getTipoRecompensa();
            }
        }
        return "";
    }

    public static int getQuantidadeRecompensaByNomeConquista(String nomeConquista) {
        for (EnumConquistas conquista : EnumConquistas.values()) {
            if (conquista.getNomeConquista().equals(nomeConquista)) {
                return conquista.getQuantidadeRecompensa();
            }
        }
        return 0;
    }

    public static EnumConquistas getNomeConquistaById(int idConquista) {
        for (EnumConquistas conquista : EnumConquistas.values()) {
            if (conquista.getId() == idConquista) {
                return conquista;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getNomeConquista() {
        return nomeConquista;
    }

    public String getDescricaoConquista() {
        return descricaoConquista;
    }

    public String getImagemConquista() {
        return imagemConquista;
    }

    public String getTipoRecompensa() {
        return tipoRecompensa;
    }

    public int getQuantidadeRecompensa() {
        return quantidadeRecompensa;
    }
}

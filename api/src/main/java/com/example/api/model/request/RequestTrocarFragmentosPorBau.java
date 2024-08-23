package com.example.api.model.request;

public class RequestTrocarFragmentosPorBau {

    private Long idDigimon;
    private int idItem;

    public RequestTrocarFragmentosPorBau() {
    }

    public RequestTrocarFragmentosPorBau(Long idDigimon, int idItem) {
        this.idDigimon = idDigimon;
        this.idItem = idItem;
    }

    public Long getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(Long idDigimon) {
        this.idDigimon = idDigimon;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
}

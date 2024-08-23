package com.example.api.model.request;

public class RequestAtivarPremium {

    private Long idDigimon;
    private int periodoDias;

    public RequestAtivarPremium() {
    }

    public RequestAtivarPremium(Long idDigimon, int periodoDias) {
        this.idDigimon = idDigimon;
        this.periodoDias = periodoDias;
    }

    public Long getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(Long idDigimon) {
        this.idDigimon = idDigimon;
    }

    public int getPeriodoDias() {
        return periodoDias;
    }

    public void setPeriodoDias(int periodoDias) {
        this.periodoDias = periodoDias;
    }

}

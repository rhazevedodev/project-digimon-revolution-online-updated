package com.example.api.modelNova.responses;

public class ResponseError {

    private String erro;

    public ResponseError(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}

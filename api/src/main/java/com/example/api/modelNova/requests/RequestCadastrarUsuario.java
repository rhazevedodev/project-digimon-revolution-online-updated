package com.example.api.modelNova.requests;

public class RequestCadastrarUsuario {

    private String usuario;
    private String email;
    private String dataNascimento;
    private String senha;

    public RequestCadastrarUsuario() {
    }

    public RequestCadastrarUsuario(String usuario, String email, String dataNascimento, String senha) {
        this.usuario = usuario;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

package com.example.api.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 4, max = 20, message = "O campo nome deve ter entre 4 e 20 caracteres")
    @JsonProperty("usuario")
    private String usuario;

    @NotBlank(message = "O campo senha é obrigatório")
    @Size(min = 6, message = "O campo senha deve ter pelo menos 6 caracteres")
    @JsonProperty("senha")
    private String senha;

    @NotBlank(message = "O campo email é obrigatório")
    @Email(message = "O campo email deve ser um email válido")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "O campo data de nascimento é obrigatório")
    @JsonProperty("dataNascimento")
    private String dataNascimento;

    private String tokenSenha;

    private LocalDateTime dataCadastro = LocalDateTime.now();

    private LocalDateTime dataUltimaAlteracao;

    private String indicacao;

    public Jogador() {
    }

    public Jogador(Long id, String usuario, String senha, String email, String dataNascimento, String tokenSenha, LocalDateTime dataCadastro, LocalDateTime dataUltimaAlteracao, String indicacao) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.tokenSenha = tokenSenha;
        this.dataCadastro = dataCadastro;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.indicacao = indicacao;
    }

    public String getIndicacao() {
        return indicacao;
    }

    public void setIndicacao(String indicacao) {
        this.indicacao = indicacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getTokenSenha() {
        return tokenSenha;
    }

    public void setTokenSenha(String tokenSenha) {
        this.tokenSenha = tokenSenha;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
}
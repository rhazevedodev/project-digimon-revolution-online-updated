package com.example.api.domain.jogador;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome é obrigatório")
    @Length(min = 4, max = 20, message = "O nome deve ter entre 4 e 20 caracteres")
    private String nome;
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    @Length(max = 50, message = "O email deve ter no máximo 50 caracteres")
    private String email;
    @NotBlank(message = "A senha é obrigatória")
    @Length(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String senha;
    @CreatedBy
    private String criadoPor;
    @CreatedDate
    private LocalDateTime criadoEm = LocalDateTime.now();

    public Jogador(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}

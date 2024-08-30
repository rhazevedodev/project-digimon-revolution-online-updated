package com.example.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Classe de configuração de segurança para a aplicação.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Bean para codificação de senhas utilizando BCrypt.
     *
     * @return Um codificador de senhas BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuração da cadeia de filtros de segurança.
     *
     * @param http O objeto HttpSecurity utilizado para configurar a segurança HTTP.
     * @return A cadeia de filtros de segurança configurada.
     * @throws Exception Se ocorrer um erro durante a configuração.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para facilitar testes, não recomendado para produção
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/jogadores/cadastrar").permitAll() // Permite acesso público à rota de cadastro
                                .anyRequest().authenticated() // Requer autenticação para qualquer outra rota
                )
                .formLogin(Customizer.withDefaults()) // Permite acesso ao formulário de login com configurações padrão
                .logout(Customizer.withDefaults()); // Permite logout com configurações padrão

        return http.build();
    }
}
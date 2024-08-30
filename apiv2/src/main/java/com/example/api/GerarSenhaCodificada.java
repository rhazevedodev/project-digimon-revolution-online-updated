package com.example.api;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenhaCodificada {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senhaPlain = "815da0d7-6d87-49e0-a5c8-8bf6c16f1082";
        String senhaCodificada = passwordEncoder.encode(senhaPlain);
        System.out.println("Senha codificada: " + senhaCodificada);
    }
}

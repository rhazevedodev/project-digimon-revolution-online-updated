package com.example.api.service;

import com.example.api.model.entity.Jogador;
import com.example.api.model.entity.Token;
import com.example.api.repository.JogadorRepository;
import com.example.api.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TokenRepository tokenRepository;

    private static final long TOKEN_VALIDITY_MINUTES = 60;

    public Optional<String> autenticar(String username, String password) {
        Optional<Jogador> jogadorOptional = jogadorRepository.findByUsuario(username);
        if (jogadorOptional.isPresent()) {
            Jogador jogador = jogadorOptional.get();
            if (jogador.getSenha().equals(password)) {
                Optional<Token> tokenOptional = tokenRepository.findByUsername(username);
                if (tokenOptional.isPresent()) {
                    Token token = tokenOptional.get();
                    if (token.getExpirationTime().isAfter(LocalDateTime.now())) {
                        return Optional.of(token.getToken());
                    } else {
                        tokenRepository.delete(token);
                    }
                }
                String newToken = generateToken();
                Token token = new Token();
                token.setToken(newToken);
                token.setUsername(username);
                token.setExpirationTime(LocalDateTime.now().plusMinutes(TOKEN_VALIDITY_MINUTES));
                tokenRepository.save(token);
                return Optional.of(newToken);
            }
        }
        return Optional.empty();
    }

    private String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[24];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
}
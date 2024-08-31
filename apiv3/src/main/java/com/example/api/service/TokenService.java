package com.example.api.service;

import com.example.api.entity.Token;
import com.example.api.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[24];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    public Optional<Token> findByUsername(String username) {
        return tokenRepository.findByUsername(username);
    }

    public void delete(Token token) {
        tokenRepository.delete(token);
    }

    public void save(Token token) {
        tokenRepository.save(token);
    }
}

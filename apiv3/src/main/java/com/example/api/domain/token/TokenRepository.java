package com.example.api.domain.token;

import java.util.Optional;

public interface TokenRepository {

    Optional<Token> findByUsername(String username);

    void save(Token token);

    void delete(Token token);
}

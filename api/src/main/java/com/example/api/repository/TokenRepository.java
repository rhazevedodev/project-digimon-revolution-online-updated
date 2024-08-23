package com.example.api.repository;

import com.example.api.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long>{
    Optional<Token> findByUsername(String username);
}

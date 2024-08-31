package com.example.api.infra.persistence.token;

import com.example.api.domain.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTokenRepository extends JpaRepository<Token, Long> {

}

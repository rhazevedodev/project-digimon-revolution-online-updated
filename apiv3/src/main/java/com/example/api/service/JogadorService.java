package com.example.api.service;

import com.example.api.entity.Jogador;
import com.example.api.entity.Token;
import com.example.api.entity.dto.RequestAutenticarJogador;
import com.example.api.repository.JogadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class JogadorService {

    private static final Logger logger = LoggerFactory.getLogger(JogadorService.class);

    private final JogadorRepository jogadorRepository;
    private final CriptografiaService criptografiaService;
    private final TokenService tokenService;

    private static final long TOKEN_VALIDITY_MINUTES = 60;

    public JogadorService(JogadorRepository jogadorRepository, CriptografiaService criptografiaService, TokenService tokenService) {
        this.jogadorRepository = jogadorRepository;
        this.criptografiaService = criptografiaService;
        this.tokenService = tokenService;
    }

    public Jogador cadastrarJogador(Jogador jogador) {
        logger.info("Cadastrando jogador com validações de negócio: nome={}, email={}", jogador.getUsuario(), jogador.getEmail());
        if (emailEmUso(jogador.getEmail())) {
            logger.warn("Tentativa de cadastro com email já em uso: {}", jogador.getEmail());
            throw new RuntimeException("Email já está em uso");
        }
        logger.info("Criptografando a senha do jogador antes de salvar");
        String senhaCriptografada = null;
        try {
            senhaCriptografada = criptografiaService.encrypt3DES(jogador.getSenha());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        jogador.setSenha(senhaCriptografada);
        return jogadorRepository.save(jogador);
    }

    public Optional<String> autenticar(RequestAutenticarJogador request) {
        Optional<Jogador> jogadorOptional = findbyUsuario(request.getUsuario());
        try {
            if (jogadorOptional.isPresent()) {
                Jogador jogador = jogadorOptional.get();
                if (criptografiaService.decrypt3DES(jogador.getSenha()).equals(request.getSenha())) {
                    Optional<Token> tokenOptional = tokenService.findByUsername(request.getUsuario());
                    if (tokenOptional.isPresent()) {
                        Token token = tokenOptional.get();
                        if (token.getExpirationTime().isAfter(LocalDateTime.now())) {
                            return Optional.of(token.getToken());
                        } else {
                            tokenService.delete(token);
                        }
                    }
                    String newToken = tokenService.generateToken();
                    Token token = new Token();
                    token.setToken(newToken);
                    token.setUsername(request.getUsuario());
                    token.setExpirationTime(LocalDateTime.now().plusMinutes(TOKEN_VALIDITY_MINUTES));
                    tokenService.save(token);
                    return Optional.of(newToken);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


    private boolean emailEmUso(String email) {
        return jogadorRepository.existsByEmail(email);
    }

    private Optional<Jogador> findbyUsuario(String usuario) {
        return jogadorRepository.findByUsuario(usuario);
    }

    public int getIdByUsuario(String usuario) {
        Optional<Jogador> jogador = null;
        try {
            jogador = jogadorRepository.findByUsuario(criptografiaService.decrypt3DES(usuario));
            if (jogador.isPresent()) {
                return jogador.get().getId().intValue();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}



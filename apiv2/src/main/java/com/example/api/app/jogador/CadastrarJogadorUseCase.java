package com.example.api.app.jogador;

import com.example.api.domain.jogador.Jogador;
import com.example.api.domain.jogador.JogadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por executar o caso de uso de cadastro de jogador.
 */
@Component
public class CadastrarJogadorUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CadastrarJogadorUseCase.class);

    private final JogadorService jogadorService;

    /**
     * Construtor da classe CadastrarJogadorUseCase.
     *
     * @param jogadorService Serviço de jogador utilizado para realizar o cadastro.
     */
    public CadastrarJogadorUseCase(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    /**
     * Método que executa o caso de uso para cadastrar um jogador.
     *
     * @param jogador Objeto Jogador contendo os dados do jogador a ser cadastrado.
     * @return O objeto Jogador cadastrado.
     */
    public Jogador executar(Jogador jogador) {
        // Pode ter lógica adicional aqui, como conversões de DTO, etc.
        logger.info("Executando caso de uso para cadastrar jogador: {}", jogador);
        Jogador novoJogador = jogadorService.cadastrarJogador(jogador);
        logger.info("Jogador cadastrado com sucesso: {}", novoJogador);
        return novoJogador;
    }
}
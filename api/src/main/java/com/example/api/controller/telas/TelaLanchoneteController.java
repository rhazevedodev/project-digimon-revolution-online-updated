package com.example.api.controller.telas;

import com.example.api.model.request.RequestFazerLanche;
import com.example.api.service.telas.TelaInvasoesService;
import com.example.api.service.telas.TelaLanchoneteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/telaLanchonete")
public class TelaLanchoneteController {

    private static final Logger logger = LoggerFactory.getLogger(TelaLanchoneteController.class);

    @Autowired
    private TelaLanchoneteService telaLanchoneteService;

    @PostMapping("/fazerLanche")
    public void fazerLanche(@RequestBody @Valid RequestFazerLanche request) {
        try {
            logger.info("Recebendo requisição para fazer lanche: {}", request);
            telaLanchoneteService.fazerLanche(request);
            logger.info("Lanche feito com sucesso para o Digimon com ID: {}", request.getIdDigimon());
        } catch (Exception e) {
            logger.error("Erro ao fazer lanche para o Digimon com ID: {}", request.getIdDigimon(), e);
            throw e;
        }
    }

}

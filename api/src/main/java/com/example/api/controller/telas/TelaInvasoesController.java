package com.example.api.controller.telas;

import com.example.api.model.request.RequestCarregarTelaInvasoes;
import com.example.api.service.telas.TelaInvasoesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/telaInvasao")
public class TelaInvasoesController {

    @Autowired
    private TelaInvasoesService telaInvasoesService;

    @PostMapping("/carregaTelaInvasoes")
    public Map<String,Object> carregaTelaInvasoes(@Valid @RequestBody RequestCarregarTelaInvasoes request) {
        return telaInvasoesService.carregarTelaInvasoes(request.getIdDigimon());
    }

}

package com.example.api.controller;

import com.example.api.model.ResultadoCombateInvasao;
import com.example.api.model.request.RequestIniciarCombateInvasao;
import com.example.api.service.CombateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/combate")
public class CombateController {

    @Autowired
    private CombateService combateService;

    @PostMapping("/combateInvasao")
    public ResultadoCombateInvasao combateInvasao(@Valid @RequestBody RequestIniciarCombateInvasao requestIniciarCombateInvasao) {
        return combateService.iniciarCombate1vs1Invasao(
                requestIniciarCombateInvasao.getIdDigimonAtacante());
    }

}

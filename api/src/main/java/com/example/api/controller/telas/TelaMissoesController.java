package com.example.api.controller.telas;

import com.example.api.model.request.RequestCarregarTelaMissoes;
import com.example.api.service.telas.TelaMissoesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/telaMissoes")
public class TelaMissoesController {

    @Autowired
    private TelaMissoesService telaMissoesService;

    @PostMapping("/carregaTelaMissoes")
    public Map<String,Object> carregaTelaMissoes(@Valid @RequestBody RequestCarregarTelaMissoes request) {
        return telaMissoesService.carregarTelaMissoes(request.getIdDigimon());
    }
}

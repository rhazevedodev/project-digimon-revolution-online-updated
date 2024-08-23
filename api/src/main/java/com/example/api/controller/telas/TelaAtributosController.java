package com.example.api.controller.telas;

import com.example.api.model.request.RequestCarregarTelaAtributos;
import com.example.api.model.request.RequestSalvarAtributos;
import com.example.api.service.telas.TelaAtributosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/telaAtributos")
public class TelaAtributosController {

    @Autowired
    private TelaAtributosService telaAtributosService;

    @PostMapping("/carregaTelaAtributos")
    public Map<String,Object> carregaTelaAtributos(@Valid @RequestBody RequestCarregarTelaAtributos request) {
        return telaAtributosService.carregarTelaAtributos(request.getIdDigimon());
    }

    @PostMapping("salvarAtributos")
    public Map<String, Object> salvarAtributos(@Valid @RequestBody RequestSalvarAtributos request) {
        return telaAtributosService.salvarAtributos(request);
    }

}

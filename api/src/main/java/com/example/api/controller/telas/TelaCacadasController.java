package com.example.api.controller.telas;

import com.example.api.model.request.RequestCarregarTelaCacadas;
import com.example.api.service.telas.TelaCacadasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/telaCacadas")
public class TelaCacadasController {

    @Autowired
    private TelaCacadasService telaCacadasService;

    @PostMapping("/carregaTelaCacadas")
    public Map<String,Object> carregaTelaCacadas(@Valid @RequestBody RequestCarregarTelaCacadas request) {
        return telaCacadasService.carregarTelaCacadas(request.getIdDigimon());
    }

}

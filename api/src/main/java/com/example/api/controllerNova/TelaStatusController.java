package com.example.api.controllerNova;

import com.example.api.model.request.RequestCarregarTelaStatus;
import com.example.api.serviceNova.TelaStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/telaStatus")
public class TelaStatusController {

    @Autowired
    private TelaStatusService telaStatusService;

    @PostMapping("/carregarInformacoes")
    public Map<String, Object> carregarTelaStatus(@Valid @RequestBody RequestCarregarTelaStatus request) {
        return telaStatusService.carregarTelaStatus(request.getIdDigimon());
    }


}

package com.example.api.controller.telas;

import com.example.api.model.request.RequestCarregarTelaStatus;
import com.example.api.service.DigimonService;
import com.example.api.service.telas.TelaStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/telaStatus")
public class TelaStatusController {

    @Autowired
    private TelaStatusService telaStatusService;

    @PostMapping("/carregarTelaStatus")
    public Map<String, Object> carregarTelaStatus(@Valid @RequestBody RequestCarregarTelaStatus request) {
        return telaStatusService.carregarTelaStatus(request.getIdDigimon());
    }
}

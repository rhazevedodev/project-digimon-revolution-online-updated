package com.example.api.controller;

import com.example.api.model.request.RequestAtivarPremium;
import com.example.api.model.request.RequestBuscarPeriodoPremium;
import com.example.api.model.request.RequestValidarPremium;
import com.example.api.service.PremiumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/premium")
public class PremiumController {

    @Autowired
    private PremiumService premiumService;

    @PostMapping("/buscarPeriodoPremium")
    public Map<String, Object> buscarPeriodoPremium(@Valid @RequestBody RequestBuscarPeriodoPremium request) {
        return premiumService.buscarPeriodoPremium(request.getIdDigimon());
    }

    @PostMapping("/ativarPremium")
    public Map<String,Object> ativarPremium(@Valid @RequestBody RequestAtivarPremium request) {
        return premiumService.ativarPremium(request);
    }

    @GetMapping("/validarPremium")
    public String validarPremium(@Valid @RequestBody RequestValidarPremium request) {
        return premiumService.validarPremium(request.getIdDigimon());
    }

}

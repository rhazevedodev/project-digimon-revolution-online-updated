package com.example.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class DefaultController {

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "API em funcionamento";
    }
}

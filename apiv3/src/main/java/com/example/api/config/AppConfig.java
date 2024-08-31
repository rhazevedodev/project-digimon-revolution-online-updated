package com.example.api.config;

import com.example.api.enumerator.EnumDigimonRookie;
import com.example.api.service.LogService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    private LogService logService;

    @PostConstruct
    public void init() {
        EnumDigimonRookie.setLogService(logService);
    }

}

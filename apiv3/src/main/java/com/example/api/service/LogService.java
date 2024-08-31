package com.example.api.service;

import com.example.api.entity.Log;
import com.example.api.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService implements LogServiceInterface{

    @Autowired
    private LogRepository logRepository;

    // Usando o SLF4J para o log
    private static final Logger logger = (Logger) LoggerFactory.getLogger(LogService.class);

    public void logAction(String action, String details) {
        Log log = new Log(action, details);
        logRepository.save(log);
    }

    @Override
    public void logError(String message, Exception e) {
        logger.error(message, e);
    }
}
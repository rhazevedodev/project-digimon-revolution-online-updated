package com.example.api.exception;

import com.example.api.model.ErrorDetails;
import com.example.api.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private LogService logService;

    @ExceptionHandler(DigimonSemVidaException.class)
    public ResponseEntity<Object> handleDigimonSemVidaException(DigimonSemVidaException ex) {
        // Constrói a resposta personalizada
        var errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "Verifique os pontos de vida do Digimon antes de iniciar o combate");

        // Retorna a resposta com status 400 BAD REQUEST
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        logService.logAction("Exception Handler - Erro", errors.toString());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
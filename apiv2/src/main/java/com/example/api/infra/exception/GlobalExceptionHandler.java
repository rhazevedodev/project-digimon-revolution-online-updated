package com.example.api.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que trata exceções globais na aplicação.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções de validação de argumentos de método.
     *
     * @param ex A exceção lançada quando a validação falha.
     * @return Um ResponseEntity contendo os erros de validação e o status HTTP BAD\_REQUEST.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções globais não específicas.
     *
     * @param ex A exceção lançada.
     * @param request O objeto WebRequest associado à exceção.
     * @return Um ResponseEntity contendo os detalhes do erro e o status HTTP INTERNAL\_SERVER\_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Trata exceções de tempo de execução.
     *
     * @param ex A exceção lançada.
     * @param request O objeto WebRequest associado à exceção.
     * @return Um ResponseEntity contendo a mensagem de erro e o status HTTP BAD\_REQUEST.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Você pode adicionar mais métodos para tratar exceções específicas, como NotFoundException, etc.
}
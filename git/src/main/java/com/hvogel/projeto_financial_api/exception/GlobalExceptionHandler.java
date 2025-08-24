package com.hvogel.projeto_financial_api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler<T> {
	
	@ExceptionHandler(CreditoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(CreditoNotFoundException ex) {
        // Retornar 404 Not Found com mensagem de erro customizada
        ErrorResponse errorResponse = new ErrorResponse("Crédito não encontrado", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Processar demais exception handlers ...
    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(T ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ((Throwable) ex).getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}

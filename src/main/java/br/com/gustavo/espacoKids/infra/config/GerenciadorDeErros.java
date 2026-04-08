package br.com.gustavo.espacoKids.infra.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GerenciadorDeErros {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> tratarResponseStatus(ResponseStatusException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", ex.getStatusCode().value());
        body.put("mensagem", ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("erros", ex.getBindingResult().getFieldErrors().stream()
                .map(e -> Map.of("campo", e.getField(), "mensagem", e.getDefaultMessage()))
                .toList());
        return ResponseEntity.badRequest().body(body);
    }
}

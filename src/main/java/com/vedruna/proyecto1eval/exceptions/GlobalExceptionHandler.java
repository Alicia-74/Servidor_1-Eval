package com.vedruna.proyecto1eval.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

     // Manejo de excepción para email duplicado
     @ExceptionHandler(DuplicateEmailException.class)
     public ResponseEntity<Map<String, Object>> handleDuplicateEmailException(DuplicateEmailException ex) {
         Map<String, Object> response = new HashMap<>();
         response.put("status", 409); // Código 409 para conflicto
         response.put("message", ex.getMessage());
         return new ResponseEntity<>(response, HttpStatus.CONFLICT);
     }
 
     // Manejo de excepción para Linkedin duplicado
     @ExceptionHandler(DuplicateLinkedinException.class)
     public ResponseEntity<Map<String, Object>> handleDuplicateLinkedinException(DuplicateLinkedinException ex) {
         Map<String, Object> response = new HashMap<>();
         response.put("status", 409); // Código 409 para conflicto
         response.put("message", ex.getMessage());
         return new ResponseEntity<>(response, HttpStatus.CONFLICT);
     }
 
     // Manejo de excepción para Github duplicado
     @ExceptionHandler(DuplicateGithubException.class)
     public ResponseEntity<Map<String, Object>> handleDuplicateGithubException(DuplicateGithubException ex) {
         Map<String, Object> response = new HashMap<>();
         response.put("status", 409); // Código 409 para conflicto
         response.put("message", ex.getMessage());
         return new ResponseEntity<>(response, HttpStatus.CONFLICT);
     }
 

    // Manejo de excepción para errores en el procesamiento de recursos
    @ExceptionHandler(ResourceProcessingException.class)
    public ResponseEntity<Map<String, Object>> handleResourceProcessingException(ResourceProcessingException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 422); // Código 422 para error en procesamiento
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Manejo de excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 500); // Código 500 para error interno del servidor
        response.put("message", "Ocurrió un error inesperado: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

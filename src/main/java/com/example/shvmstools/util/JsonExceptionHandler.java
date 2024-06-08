package com.example.shvmstools.util;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class JsonExceptionHandler {

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Map<String, Object>> handleJsonProcessingException(JsonProcessingException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Invalid JSON format");
        response.put("message", e.getOriginalMessage());
        response.put("location", e.getLocation());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

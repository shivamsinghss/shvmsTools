package com.example.shvmstools.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class JsonFormatterController {

    private final ObjectMapper objectMapper;

    public JsonFormatterController() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @PostMapping("/formatJson")
    public ResponseEntity<Map<String, Object>> formatJson(@RequestBody String json) {
        Map<String, Object> response = new HashMap<>();
        try {
            Object jsonObject = objectMapper.readValue(json, Object.class);
            String formattedJson = objectMapper.writeValueAsString(jsonObject);
            response.put("formattedJson", formattedJson);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            response.put("error", "Invalid JSON format");
            response.put("message", e.getOriginalMessage());
            response.put("location", e.getLocation());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}


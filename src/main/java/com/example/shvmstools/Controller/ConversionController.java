package com.example.shvmstools.Controller;

import com.example.shvmstools.Service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class ConversionController {

    @Autowired
    private ConversionService conversionService;

    @PostMapping("/json2xml")
    public ResponseEntity<String> jsonToXml(@RequestBody String json) {
        try {
            String xml = conversionService.jsonToXml(json);
            return ResponseEntity.ok(xml);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid JSON input");
        }
    }

    @PostMapping("/xml2json")
    public ResponseEntity<String> xmlToJson(@RequestBody String xml) {
        try {
            String json = conversionService.xmlToJson(xml);
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid XML input");
        }
    }

    @PostMapping("/map2json")
    public ResponseEntity<String> mapToJson(@RequestBody Map<String, Object> map) {
        try {
            String json = conversionService.mapToJson(map);
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid Map input");
        }
    }
}

package com.example.shvmstools.Controller;

import com.example.shvmstools.Dto.OriginalUrlResponse;
import com.example.shvmstools.Entity.UrlMappingEntity;
import com.example.shvmstools.Repository.UrlMappingRepository;
import com.example.shvmstools.Service.UrlShortenerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@CrossOrigin(origins = "*")

public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody String originalUrl) {
        String encodedUrl = URLEncoder.encode(originalUrl, StandardCharsets.UTF_8);
        UrlMappingEntity existingUrl = urlMappingRepository.findByOriginalUrl(originalUrl);
        if (existingUrl != null) {
            return existingUrl.getShortUrl();
        }
        else {
            return urlShortenerService.shortenUrl(originalUrl);
        }
    }

@GetMapping("/redirect")
public void redirect(@RequestParam("shortUrl") String shortUrlParam, HttpServletResponse response) throws IOException {
    String jsonResponse = urlShortenerService.getOriginalUrl(shortUrlParam);
    ObjectMapper objectMapper = new ObjectMapper();
    OriginalUrlResponse originalUrlResponse = objectMapper.readValue(jsonResponse, OriginalUrlResponse.class);
    String originalUrl = originalUrlResponse.getOriginalUrl();
    if (originalUrl != null) {
        response.sendRedirect(originalUrl);
    } else {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}

}


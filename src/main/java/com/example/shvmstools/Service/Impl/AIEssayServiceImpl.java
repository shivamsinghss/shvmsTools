package com.example.shvmstools.Service.Impl;

import com.example.shvmstools.Dto.AIEssayDTO;
import com.example.shvmstools.Dto.AIEssayURI;
import com.example.shvmstools.Dto.Content;
import com.example.shvmstools.Dto.Part;
import com.example.shvmstools.Service.AIEssayService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class AIEssayServiceImpl implements AIEssayService {

    private static final Logger logger = LoggerFactory.getLogger(AIEssayServiceImpl.class);


    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String apiUrl;

    public AIEssayServiceImpl(RestTemplate restTemplate, @Value("${gemini.api.key}") String apiKey,
                              @Value("${gemini.api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
    }

    @Override
    public String callGeminiApi(AIEssayDTO requestBody) {
        StringBuilder extractedText = new StringBuilder();
        Part part = new Part();
        String searchText="write a "+requestBody.getType()+" essay on a topic "+requestBody.getTopic()+" with greater than or equal to "+requestBody.getWordCount()+ " words";
        logger.info("searched value in gemini AI :::::::::: {}", searchText);
        part.setText(searchText);
        Content content = new Content();
        content.setParts(Collections.singletonList(part));
        AIEssayURI requestedBody = new AIEssayURI();
        requestedBody.setContents(Collections.singletonList(content));
        String fullUrl = apiUrl + "?key=" + apiKey;
        AIEssayURI aiEssayURI = new AIEssayURI();
        aiEssayURI.setContents(Collections.singletonList(content));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AIEssayURI> request = new HttpEntity<>(requestedBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(fullUrl, request, String.class);
        String responseBody = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        String resultValue ="Failed to fetch data from AI";
        try {
            JsonNode root = mapper.readTree(responseBody);
            JsonNode textNode = root.path("candidates").get(0).path("content").path("parts").get(0).path("text");
            resultValue = textNode.asText();
            logger.info("Extracted text value from gemini AI: {}", resultValue);
        } catch (Exception e) {
            logger.error("Error extracting text value: {}", e.getMessage());        }
        return resultValue;
    }
}

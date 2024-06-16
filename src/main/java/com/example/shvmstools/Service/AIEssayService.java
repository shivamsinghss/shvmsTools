package com.example.shvmstools.Service;


import com.example.shvmstools.Dto.AIEssayDTO;

public interface AIEssayService {

    String callGeminiApi(AIEssayDTO requestBody);
}

package com.example.shvmstools.Controller;


import com.example.shvmstools.Dto.AIEssayDTO;
import com.example.shvmstools.Service.AIEssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class EssayWritingApiController {
    @Autowired
    private AIEssayService AIEssayService;

    @PostMapping("/gemini-api")
    public String callExternalApi(@RequestBody AIEssayDTO requestBody) {
        return AIEssayService.callGeminiApi(requestBody);
    }
}

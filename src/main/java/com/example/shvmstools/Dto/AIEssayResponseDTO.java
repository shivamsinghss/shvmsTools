package com.example.shvmstools.Dto;

public class AIEssayResponseDTO {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "AIEssayResponseDTO{" +
                "text='" + text + '\'' +
                '}';
    }
}

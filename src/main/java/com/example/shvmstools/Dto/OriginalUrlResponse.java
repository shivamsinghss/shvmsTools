package com.example.shvmstools.Dto;

public class OriginalUrlResponse {
    private String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    @Override
    public String toString() {
        return "OriginalUrlResponse{" +
                "originalUrl='" + originalUrl + '\'' +
                '}';
    }
}

package com.example.shvmstools.Service;

import org.springframework.stereotype.Service;

@Service
public interface UrlShortenerService {

    public String shortenUrl(String originalUrl);

    String getOriginalUrl(String shortUrl);
}

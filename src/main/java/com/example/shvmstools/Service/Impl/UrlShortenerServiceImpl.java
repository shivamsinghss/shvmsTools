package com.example.shvmstools.Service.Impl;

import com.example.shvmstools.Entity.UrlMappingEntity;
import com.example.shvmstools.Repository.UrlMappingRepository;
import com.example.shvmstools.Service.UrlShortenerService;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.nio.charset.StandardCharsets;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;


    private static final String BASE_URL = "http://shvmsTools.com/";



    @Override
    public String shortenUrl(String originalUrl) {
        String shortCode = Hashing.murmur3_32().hashString(originalUrl, StandardCharsets.UTF_8).toString();
        shortCode = shortCode.substring(0, 7);
        UrlMappingEntity urlEntry = new UrlMappingEntity();
        urlEntry.setOriginalUrl(originalUrl);
        urlEntry.setShortUrl(BASE_URL + shortCode);
        urlEntry.setCreatedBy("Admin");
        urlMappingRepository.save(urlEntry);
        return urlEntry.getShortUrl();

    }

    public String getOriginalUrl(String shortUrl) {
        UrlMappingEntity urlEntry = urlMappingRepository.findByShortUrl(shortUrl);
        return (urlEntry != null) ? urlEntry.getOriginalUrl() : null;
    }
}

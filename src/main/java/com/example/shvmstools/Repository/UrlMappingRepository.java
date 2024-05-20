package com.example.shvmstools.Repository;


import com.example.shvmstools.Entity.UrlMappingEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMappingEntity, Integer> {

    UrlMappingEntity findByShortUrl(String shortUrl);

    UrlMappingEntity findByOriginalUrl(String longUrl);

}

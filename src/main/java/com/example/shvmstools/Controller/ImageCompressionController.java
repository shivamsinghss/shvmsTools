package com.example.shvmstools.Controller;

import com.example.shvmstools.Service.ImageCompressionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class ImageCompressionController {

    Logger logger = LoggerFactory.getLogger(ImageCompressionController.class);

    @Autowired
    private ImageCompressionService compressionService;


    @PostMapping("/compress")
    public ResponseEntity<byte[]> compressImage(@RequestParam("file") MultipartFile file,
                                                @RequestParam("percentage") int compressionPercentage) {
        logger.info("Received file: {}", file.getOriginalFilename());
        logger.info("Compression percentage: {}", compressionPercentage);

        try {
            byte[] compressedImage = compressionService.compressImage(file, compressionPercentage);
            logger.info("Image compressed successfully");
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(compressedImage);
        } catch (IOException e) {
            logger.error("Error while compressing image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

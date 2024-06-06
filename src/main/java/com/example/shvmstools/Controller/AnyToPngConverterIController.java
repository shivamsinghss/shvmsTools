package com.example.shvmstools.Controller;
import com.example.shvmstools.Service.AnyToPngConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class AnyToPngConverterIController {

    @Autowired
    private AnyToPngConverter imageConversionService;

    Logger logger = LoggerFactory.getLogger(AnyToPngConverterIController.class);

    @PostMapping("/convertPNG")
    public ResponseEntity<byte[]> convertToPng(@RequestParam("image") MultipartFile imageFile) {
        try {
            logger.info("Converting image to PNG with filename:::{}",imageFile.getOriginalFilename());
            byte[] pngBytes = imageConversionService.convertToPng(imageFile.getInputStream());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(pngBytes.length);
            headers.set("Content-Disposition", "attachment; filename=\"converted-shvmsTools.png\"");
            return new ResponseEntity<>(pngBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            logger.info("Error converting image to PNG with filename:::{}",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(("Kindly provide a valid image...Error:   " + e.getMessage()).getBytes());
        }
    }

    @PostMapping("/convertJPEG")
    public ResponseEntity<byte[]> convertToJPEG(@RequestParam("image") MultipartFile imageFile) {
        try {
            logger.info("Converting image to JPEG with filename:::{}",imageFile.getOriginalFilename());
            byte[] pngBytes = imageConversionService.convertToPng(imageFile.getInputStream());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(pngBytes.length);
            headers.set("Content-Disposition", "attachment; filename=\"converted-shvmsTools.jpeg\"");
            return new ResponseEntity<>(pngBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            logger.info("Error converting image to JPEG with filename:::{}",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(("Kindly provide a valid image...Error:   " + e.getMessage()).getBytes());
        }
    }
}

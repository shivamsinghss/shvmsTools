package com.example.shvmstools.Controller;

import com.example.shvmstools.Service.PdfToWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@CrossOrigin(origins = "*")

public class PdfToWordController {

    private final PdfToWordService pdfToWordService;

    @Autowired
    public PdfToWordController(PdfToWordService pdfToWordService) {
        this.pdfToWordService = pdfToWordService;
    }

    @PostMapping(value = "/pdf2word", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> convertPdfToWord(@RequestParam("file") MultipartFile file) {
        try {
            // Save the uploaded file to a temporary location
            File tempFile = File.createTempFile("result-shvmsTools", ".pdf");
            file.transferTo(tempFile);

            // Convert the PDF to Word
            File wordFile = pdfToWordService.convertPdfToWord(tempFile);

            // Prepare the response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "result-shvmsTools.docx");
            headers.setContentLength(wordFile.length());

            return new ResponseEntity<>(Files.readAllBytes(wordFile.toPath()), headers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the file");
        }
    }
}

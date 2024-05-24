package com.example.shvmstools.Service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageCompressionService {

    byte[] compressImage(MultipartFile file, int compressionPercentage) throws IOException;
}

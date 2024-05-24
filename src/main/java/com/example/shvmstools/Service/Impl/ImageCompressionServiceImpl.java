package com.example.shvmstools.Service.Impl;

import com.example.shvmstools.Service.ImageCompressionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImageCompressionServiceImpl implements ImageCompressionService {

    @Override
    public byte[] compressImage(MultipartFile file, int percentage) throws IOException {
        BufferedImage originalImage = ImageIO.read(file.getInputStream());
        int width = originalImage.getWidth() * percentage / 100;
        int height = originalImage.getHeight() * percentage / 100;

        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        resizedImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", baos);
        return baos.toByteArray();
    }
}

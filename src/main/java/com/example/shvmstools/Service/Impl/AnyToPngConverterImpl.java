package com.example.shvmstools.Service.Impl;

import com.example.shvmstools.Service.AnyToPngConverter;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class AnyToPngConverterImpl implements AnyToPngConverter {

    @Override
    public byte[] convertToPng(InputStream inputImageStream) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(inputImageStream);
        if (bufferedImage == null) {
            throw new IOException("Kindly upload any image to proceed");
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        return outputStream.toByteArray();
    }
}

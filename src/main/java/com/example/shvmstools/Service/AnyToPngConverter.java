package com.example.shvmstools.Service;

import java.io.IOException;
import java.io.InputStream;

public interface AnyToPngConverter {
    byte[] convertToPng(InputStream inputImageStream) throws IOException;
}
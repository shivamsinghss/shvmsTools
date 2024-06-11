package com.example.shvmstools.Service;

import java.io.File;
import java.io.IOException;

public interface PdfToWordService {
    File convertPdfToWord(File pdfFile) throws IOException;
}

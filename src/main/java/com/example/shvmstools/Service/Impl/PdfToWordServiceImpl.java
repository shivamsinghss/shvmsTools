package com.example.shvmstools.Service.Impl;

import com.example.shvmstools.Service.PdfToWordService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.*;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PdfToWordServiceImpl implements PdfToWordService {

    @Override
    public File convertPdfToWord(File pdfFile) throws IOException {
        if (!pdfFile.getName().endsWith(".pdf")) {
            throw new IllegalArgumentException("Invalid file format. Only PDF files are accepted.");
        }

        File wordFile = File.createTempFile("output", ".docx");
        try (PDDocument pdfDocument = PDDocument.load(pdfFile);
             XWPFDocument wordDocument = new XWPFDocument();
             FileOutputStream out = new FileOutputStream(wordFile)) {

            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setSortByPosition(true);
            String pdfText = pdfStripper.getText(pdfDocument);

            String[] lines = pdfText.split("\n");
            for (String line : lines) {
                XWPFParagraph paragraph = wordDocument.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(line);

                // Additional formatting can be applied here
                run.setFontFamily("Courier"); // Use a monospaced font for better alignment
                run.setFontSize(12); // Set a default font size
            }

            XWPFParagraph finalParagraph = wordDocument.createParagraph();
            XWPFRun finalRun = finalParagraph.createRun();
            finalRun.setText("Converted from PDF to Word by shvmsTools");
            finalRun.setBold(true);
            finalRun.setTextHighlightColor("red");
            finalRun.setFontSize(22);

            wordDocument.write(out);
        }

        return wordFile;
    }
}

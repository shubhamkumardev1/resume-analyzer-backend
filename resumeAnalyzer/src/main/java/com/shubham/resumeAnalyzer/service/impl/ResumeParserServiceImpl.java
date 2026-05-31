package com.shubham.resumeAnalyzer.service.impl;

import com.shubham.resumeAnalyzer.service.ResumeParserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class ResumeParserServiceImpl
        implements ResumeParserService {

    @Override
    public String extractText(
            String filePath) {

        try {

            File file =
                    new File(filePath);

            PDDocument document =
                    Loader.loadPDF(file);

            PDFTextStripper stripper =
                    new PDFTextStripper();

            String text =
                    stripper.getText(document);

            document.close();

            return text;

        } catch (IOException ex) {

            throw new RuntimeException(
                    "Failed to parse PDF"
            );
        }
    }
}
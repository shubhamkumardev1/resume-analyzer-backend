package com.shubham.resumeAnalyzer.service.impl;

import com.shubham.resumeAnalyzer.exception.FileStorageException;
import com.shubham.resumeAnalyzer.exception.InvalidFileException;
import com.shubham.resumeAnalyzer.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageServiceImpl
        implements FileStorageService {

    private static final String
            UPLOAD_DIR = "uploads";

    @Override
    public String storeFile(
            MultipartFile file) {

        validateFile(file);

        try {

            Path uploadPath =
                    Paths.get(
                            UPLOAD_DIR
                    );

            if (!Files.exists(
                    uploadPath)) {

                Files.createDirectories(
                        uploadPath
                );
            }

            String fileName =
                    System.currentTimeMillis()
                            + "_"
                            + file.getOriginalFilename();

            Path filePath =
                    uploadPath.resolve(
                            fileName
                    );

            Files.copy(
                    file.getInputStream(),
                    filePath
            );

            return filePath.toString();

        } catch (IOException ex) {

            throw new FileStorageException(
                    "Failed to store file"
            );
        }
    }

    private void validateFile(
            MultipartFile file) {

        if (file.isEmpty()) {

            throw new InvalidFileException(
                    "File cannot be empty"
            );
        }

        String fileName =
                file.getOriginalFilename();

        if (fileName == null
                ||
                (!fileName.endsWith(".pdf")
                        &&
                        !fileName.endsWith(".docx"))) {

            throw new InvalidFileException(
                    "Only PDF and DOCX files are allowed"
            );
        }
    }
}
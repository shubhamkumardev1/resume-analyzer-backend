package com.shubham.resumeAnalyzer.exception;

public class FileStorageException
        extends RuntimeException {

    public FileStorageException(
            String message) {

        super(message);
    }
}
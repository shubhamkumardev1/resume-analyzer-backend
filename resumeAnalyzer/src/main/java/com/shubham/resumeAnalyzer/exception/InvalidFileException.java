package com.shubham.resumeAnalyzer.exception;

public class InvalidFileException
        extends RuntimeException {

    public InvalidFileException(
            String message) {

        super(message);
    }
}
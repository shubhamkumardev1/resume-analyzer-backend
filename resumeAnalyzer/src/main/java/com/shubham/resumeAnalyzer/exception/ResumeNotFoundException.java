package com.shubham.resumeAnalyzer.exception;

public class ResumeNotFoundException
        extends RuntimeException {

    public ResumeNotFoundException(
            String message) {

        super(message);
    }
}
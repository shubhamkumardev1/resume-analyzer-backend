package com.shubham.resumeAnalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }
    @ExceptionHandler(
            ResumeNotFoundException.class
    )
    public ResponseEntity<String>
    handleResumeNotFoundException(
            ResumeNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
    @ExceptionHandler(
            InvalidFileException.class
    )
    public ResponseEntity<String>
    handleInvalidFileException(
            InvalidFileException ex) {

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
    @ExceptionHandler(
            FileStorageException.class
    )
    public ResponseEntity<String>
    handleFileStorageException(
            FileStorageException ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }
}

package com.example.demo.student.exception;

public class StudenNotFoundException extends RuntimeException {
    String message;

    public StudenNotFoundException(String message) {

        super(message);
    }
}

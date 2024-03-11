package br.com.joaoaugusto.springbootapplication.exception;

import org.springframework.http.HttpStatus;

// Serve para fazer exceptions personalizadas

public class AppException extends RuntimeException {

    private HttpStatus statusCode;

    // Constructor

    public AppException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}

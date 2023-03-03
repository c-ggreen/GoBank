package com.chadwick.GoBankDB.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
//this is the class that defines what all the errors look like
public class ApiException {
    private final String message;

    private final int code;
    private final HttpStatus httpStatus;

    private final ZonedDateTime timestamp;

    public ApiException(String message, int code, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}

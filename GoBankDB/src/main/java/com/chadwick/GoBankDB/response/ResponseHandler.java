package com.chadwick.GoBankDB.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ResponseHandler {
    public static ResponseEntity<Object> apiResponse(String message, Object responseObj) {
        HttpStatus status = HttpStatus.OK;
        ApiResponse apiResponse = new ApiResponse(
                message,
                status.value(),
                responseObj,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiResponse, status);
    }
}

package com.stevenson.parcel.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRejectException.class})
    public ResponseEntity<Object> handleApiStorageException(ApiRejectException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        // create payload with details
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        // return response
        return new ResponseEntity<>(apiExceptionResponse, badRequest);
    }
}

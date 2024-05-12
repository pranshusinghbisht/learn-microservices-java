package com.user.service.userservice.exceptions;

import com.user.service.userservice.payload.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException e){
        String message = e.getMessage();
        ApiResponse response = ApiResponse.builder()
                .message(message)
                .status(true) // Set to true for success
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);

    }
}

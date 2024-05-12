package com.service.hotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundHandler(ResourceNotFoundException e){
        Map map = new HashMap();
        map.put("message", e.getMessage());
        map.put("success", false);
        map.put("status", HttpStatus.NOT_FOUND);
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
}


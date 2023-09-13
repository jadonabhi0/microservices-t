package com.hotel.service.exception;/*
    @author jadon
*/

import com.hotel.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourseNotFoundExceptionHandler(ResourceNotFoundException e){
        String message = e.getMessage();
        ApiResponse build = ApiResponse.builder().success(true).status(HttpStatus.NOT_FOUND).message(message).build();
        return new ResponseEntity<>(build, HttpStatus.NOT_FOUND);
    }


}

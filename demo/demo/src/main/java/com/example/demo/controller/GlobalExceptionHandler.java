package com.example.demo.controller;

import com.example.demo.exception.ItemNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.demo.vo.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ItemNotFound.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerUserNotFound(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}

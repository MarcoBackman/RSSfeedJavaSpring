package com.example.webapplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;

@RestControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleNotFoundError(NoHandlerFoundException ex) {
        return "path does not exists";
    }

    @ExceptionHandler(ServletException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleServletError(NoHandlerFoundException ex) {
        return "path does not exists";
    }
}
package com.ac2.ac2.controllers;


import com.ac2.ac2.exceptions.ApiErrors;
import com.ac2.ac2.exceptions.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationControllerAdvice {
    
  
    @ExceptionHandler(RegraNegocioException.class)
    public ApiErrors handlerRegraNegocioException(RegraNegocioException ex) {
        return new ApiErrors(ex.getMessage());
    }
}
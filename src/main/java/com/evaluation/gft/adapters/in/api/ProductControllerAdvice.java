package com.evaluation.gft.adapters.in.api;

import com.evaluation.gft.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<String> productNotFoundExceptionHandler() {
        return new ResponseEntity<>(
                "No product with this characteristics was found",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NoResourceFoundException.class})
    public ResponseEntity<String> resourceNotFoundExceptionHandler(NoResourceFoundException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
}

package com.auth.auth.infrastructure.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseHandler.class)
    @ResponseBody
    public ResponseEntity<ErrorBase> handleApiException(ResponseHandler ex) {
        ErrorBase errorResponse = new ErrorBase(
                ex.getMessage(),
                ex.getDetails(),
                ex.getErrorId()
        );

        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorBase> handleGeneralException(Exception ex) {
        ErrorBase errorResponse = new ErrorBase(
                "An unexpected error occurred",
                "An internal server error has occurred, contact an administrator.",
                0
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

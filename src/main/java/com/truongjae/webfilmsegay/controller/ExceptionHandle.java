package com.truongjae.webfilmsegay.controller;

import com.truongjae.webfilmsegay.exception.APIError;
import com.truongjae.webfilmsegay.exception.BadRequestException;
import com.truongjae.webfilmsegay.exception.OKException;
import com.truongjae.webfilmsegay.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(OKException.class)
    public ResponseEntity<APIError> okException(OKException e){
        APIError error = new APIError();
        error.setMessage(e.getMessage());
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<APIError> objectNotFoundException(ObjectNotFoundException e){
        APIError error = new APIError();
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<APIError> badRequestException(BadRequestException e){
        APIError error = new APIError();
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

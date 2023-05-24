package com.retail.ItemService.controller;

import com.retail.ItemService.ResponseError.EmptyRequestException;
import com.retail.ItemService.ResponseError.NotFoundException;
import lombok.Data;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @Data
    public class Message {
        private String error;

        public Message(String error) {
            this.error = error;
        }
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(Exception ex, WebRequest request) {
        String errorMessageDescription = ex.getMessage();
        Message errorMessage = new Message(errorMessageDescription);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {EmptyRequestException.class})
    public ResponseEntity<?> handleEmptyBodyException(Exception ex, WebRequest request) {
        String errorMessageDescription = ex.getMessage();
        Message errorMessage = new Message(errorMessageDescription);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}

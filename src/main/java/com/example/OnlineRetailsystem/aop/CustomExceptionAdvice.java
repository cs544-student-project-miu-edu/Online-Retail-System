package com.example.OnlineRetailsystem.aop;

import com.example.OnlineRetailsystem.dto.CustomMessageType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(CustomMessageType.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCustomException(CustomMessageType ex) {
        return ex.getMessage();
    }
}
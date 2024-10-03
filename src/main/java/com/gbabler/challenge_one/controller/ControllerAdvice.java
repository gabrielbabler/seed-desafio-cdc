package com.gbabler.challenge_one.controller;

import com.gbabler.challenge_one.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return List.of(new ErrorMessage(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()));
    }
}

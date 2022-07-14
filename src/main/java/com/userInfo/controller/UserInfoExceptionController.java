package com.userInfo.controller;

import com.userInfo.exception.UserInfoException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserInfoExceptionController {

    @ExceptionHandler(UserInfoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response UserInfoException(Exception e) {
        return new Response("500", e.getMessage());
    }

    @Data
    @AllArgsConstructor
    static class Response {
        private String errorCode;
        private String message;
    }
}

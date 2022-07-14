package com.userInfo.exception;

import lombok.Getter;

@Getter
public class UserInfoException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public UserInfoException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

}

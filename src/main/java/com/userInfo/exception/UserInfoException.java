package com.userInfo.exception;

import lombok.Getter;

@Getter
public class UserInfoException extends RuntimeException {

    private ErrorCode errorCode;
    private String detailMessage;

    public UserInfoException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }

}

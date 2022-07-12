package com.userInfo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NO_USER_INFO("해당 회원이 존재하지 않습니다.");

    private final String message;
}

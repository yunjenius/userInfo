package com.userInfo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NO_USER_INFO("해당 회원이 존재하지 않습니다."),
    WRONG_PASSWORD("비밀번호가 일치하지 않습니다."),
    DUPLICATED_USER_INFO("이미 존재하는 회원입니다."),
    DUPLICATED_USER_EMAIL("이미 존재하는 E-MAIL 입니다.");

    private final String message;
}

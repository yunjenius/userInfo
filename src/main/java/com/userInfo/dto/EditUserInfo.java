package com.userInfo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditUserInfo {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request {

        @NotNull
        @Size(min = 8, max = 20, message = "userPassword size must 8 to 20")
        private String userPwd;
    }

}

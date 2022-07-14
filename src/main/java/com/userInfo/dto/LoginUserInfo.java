package com.userInfo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginUserInfo {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        @NotNull
        private String loginOption;

        private String userEmail;
        private String userPhone;

        @NotNull
        @Size(min = 8, max = 20, message = "userPassword size must 8 to 20")
        private String userPwd;

    }

}

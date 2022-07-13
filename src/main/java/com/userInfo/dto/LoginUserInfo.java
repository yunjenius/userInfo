package com.userInfo.dto;

import com.userInfo.entity.UserInfo;
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

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String userEmail;
        private String userNickname;
        private String userName;
        private String userPhone;
        private String userPwd;

        public static Response fromEntity(@NonNull UserInfo userInfo) {
            return Response.builder()
                    .userEmail(userInfo.getUserEmail())
                    .userNickname(userInfo.getUserNickname())
                    .userName(userInfo.getUserName())
                    .userPhone(userInfo.getUserPhone())
                    .userPwd(userInfo.getUserPwd())
                    .build();
        }

    }
}

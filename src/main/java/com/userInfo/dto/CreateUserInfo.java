package com.userInfo.dto;

import com.userInfo.entity.UserInfo;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserInfo {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        @NotNull
        private String userEmail;

        @NotNull
        @Size(min = 2, max = 20, message = "userNickname size must 2 to 20")
        private String userNickname;

        @NotNull
        @Size(min = 8, max = 20, message = "userPassword size must 8 to 20")
        private String userPwd;

        @NotNull
        @Size(min = 2, max = 20, message = "userName size must 2 to 20")
        private String userName;

        @NotNull
        private String userPhone;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String userEmail;
        private String userNickname;
        private String userPwd;
        private String userName;
        private String userPhone;

        public static Response fromEntity(@NonNull UserInfo userInfo) {
            return Response.builder()
                    .userEmail(userInfo.getUserEmail())
                    .userNickname(userInfo.getUserNickname())
                    .userPwd(userInfo.getUserPwd())
                    .userName(userInfo.getUserName())
                    .userPhone(userInfo.getUserPhone())
                    .build();
        }

    }
}

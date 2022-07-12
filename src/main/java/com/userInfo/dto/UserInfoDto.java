package com.userInfo.dto;

import com.userInfo.entity.UserInfo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDto {

    private String userEmail;
    private String userNickname;
    private String userPwd;
    private String userName;
    private String userPhone;

    public static UserInfoDto fromEntity(UserInfo userInfo) {
        return UserInfoDto.builder()
                .userEmail(userInfo.getUserEmail())
                .userNickname(userInfo.getUserNickname())
                .userPwd(userInfo.getUserPwd())
                .userName(userInfo.getUserName())
                .userPhone(userInfo.getUserPhone())
                .build();
    }
}

package com.userInfo.service;

import com.userInfo.dto.CreateUserInfo;
import com.userInfo.dto.EditUserInfo;
import com.userInfo.dto.LoginUserInfo;
import com.userInfo.dto.UserInfoDto;
import com.userInfo.entity.UserInfo;
import com.userInfo.exception.UserInfoException;
import com.userInfo.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.userInfo.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public int authUserPhone(String chkOption, String userPhone) {
        UserInfoDto user = UserInfoDto.fromEntity(getUserInfoByPhone(userPhone));

        if ("J".equals(chkOption) && !"none".equals(user.getUserPhone()))
            throw new UserInfoException(DUPLICATED_USER_INFO);
        if ("R".equals(chkOption) && "none".equals(user.getUserPhone())) throw new UserInfoException(NO_USER_INFO);

        return (int) (Math.random() * (999999 - 100000 + 1)) + 100000;
    }

    public String checkUserEmail(String userEmail) {
        UserInfoDto user = UserInfoDto.fromEntity(getUserInfoByEmail(userEmail));
        if (!"none".equals(user.getUserPhone())) return "fail";

        return "success";
    }

    @Transactional
    public CreateUserInfo.Response createUser(CreateUserInfo.Request request) {
        return CreateUserInfo.Response.fromEntity(
                userInfoRepository.save(createUserFromRequest(request))
        );
    }

    private UserInfo createUserFromRequest(CreateUserInfo.Request request) {
        return UserInfo.builder()
                .userEmail(request.getUserEmail())
                .userName(request.getUserName())
                .userNickname(request.getUserNickname())
                .userPhone(request.getUserPhone())
                .userPwd(request.getUserPwd())
                .build();
    }

    @Transactional
    public UserInfoDto updateUserPwd(String userEmail, EditUserInfo.Request request) {
        return UserInfoDto.fromEntity(
                getUpdateUserInfoFromRequest(
                        request, getUserInfoByEmail(userEmail)
                )
        );
    }

    private UserInfo getUpdateUserInfoFromRequest(EditUserInfo.Request request, UserInfo userInfo) {
        userInfo.setUserPwd(request.getUserPwd());
        return userInfo;
    }

    @Transactional(readOnly = true)
    public UserInfoDto loginUser(LoginUserInfo.Request request) {
        UserInfoDto user = "P".equals(request.getLoginOption()) ?
                UserInfoDto.fromEntity(getUserInfoByPhone(request.getUserPhone()))
                : UserInfoDto.fromEntity(getUserInfoByEmail(request.getUserEmail()));

        if ("none".equals(user.getUserPhone())) throw new UserInfoException(NO_USER_INFO);

        if (!request.getUserPwd().equals(user.getUserPwd())) throw new UserInfoException(WRONG_PASSWORD);

        return user;
    }

    @Transactional(readOnly = true)
    public UserInfoDto getUserInfo(String userEmail) {
        return UserInfoDto.fromEntity(getUserInfoByEmail(userEmail));
    }

    private UserInfo getUserInfoByEmail(String email) {
        return userInfoRepository.findByUserEmail(email)
                .orElse(UserInfo.builder()
                        .userPhone("none")
                        .build());
    }

    private UserInfo getUserInfoByPhone(String phone) {
        return userInfoRepository.findByUserPhone(phone)
                .orElse(UserInfo.builder()
                        .userPhone("none")
                        .build());
    }

}

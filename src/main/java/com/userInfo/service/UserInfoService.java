package com.userInfo.service;

import com.userInfo.dto.CreateUserInfo;
import com.userInfo.dto.EditUserInfo;
import com.userInfo.dto.UserInfoDto;
import com.userInfo.entity.UserInfo;
import com.userInfo.exception.UserInfoException;
import com.userInfo.repository.UserInfoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.userInfo.exception.ErrorCode.NO_USER_INFO;

@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Transactional
    public CreateUserInfo.Response createUser(CreateUserInfo.Request request) {
        validateCreateUserRequest(request);
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

    private void validateCreateUserRequest(@NonNull CreateUserInfo.Request request) {

    }

    @Transactional(readOnly = true)
    public UserInfoDto getUserInfo(String userEmail) {
        return UserInfoDto.fromEntity(getUserInfoByUserEmail(userEmail));
    }

    private UserInfo getUserInfoByUserEmail(String userEmail) {
        return userInfoRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserInfoException(NO_USER_INFO));
    }

    @Transactional
    public UserInfoDto resetUserPwd(String userEmail, EditUserInfo.Request request) {
        return UserInfoDto.fromEntity(
                getUpdateUserInfoFromRequest(request, getUserInfoByUserEmail(userEmail))
        );
    }

    private UserInfo getUpdateUserInfoFromRequest(EditUserInfo.Request request, UserInfo userInfo) {
        userInfo.setUserPwd(request.getUserPwd());
        return userInfo;
    }


}

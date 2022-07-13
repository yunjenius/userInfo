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
        UserInfoDto user = UserInfoDto.fromEntity(userInfoRepository.findByUserPhone(userPhone));

        if ("J".equals(chkOption) && user != null) throw new UserInfoException(DUPLICATED_USER_INFO);
        if ("R".equals(chkOption) && user == null) throw new UserInfoException(NO_USER_INFO);

        return (int) (Math.random() * (99999 - 10000 + 1)) + 10000;
    }

    public String checkUserEmail(String userEmail) {
        UserInfoDto user = UserInfoDto.fromEntity(userInfoRepository.findByUserEmail(userEmail));

        if (user != null) throw new UserInfoException(DUPLICATED_USER_INFO);

        return "success";
    }

    @Transactional
    public CreateUserInfo.Response createUser(CreateUserInfo.Request request) {
        UserInfoDto user = UserInfoDto.fromEntity(userInfoRepository.findByUserEmail(request.getUserEmail()));

        if (user != null) throw new UserInfoException(DUPLICATED_USER_EMAIL);

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
    public UserInfoDto resetUserPwd(String userEmail, EditUserInfo.Request request) {
        return UserInfoDto.fromEntity(
                getUpdateUserInfoFromRequest(request, userInfoRepository.findByUserEmail(userEmail))
        );
    }

    private UserInfo getUpdateUserInfoFromRequest(EditUserInfo.Request request, UserInfo userInfo) {
        userInfo.setUserPwd(request.getUserPwd());
        return userInfo;
    }

    @Transactional(readOnly = true)
    public LoginUserInfo.Response loginUser(LoginUserInfo.Request request) {
        LoginUserInfo.Response userInfo = ("E".equals(request.getLoginOption())) ?
                LoginUserInfo.Response.fromEntity(userInfoRepository.findByUserEmail(request.getUserEmail()))
                : LoginUserInfo.Response.fromEntity(userInfoRepository.findByUserPhone(request.getUserPhone()));

        if (userInfo == null) throw new UserInfoException(NO_USER_INFO);
        if (!request.getUserPwd().equals(userInfo.getUserPwd())) throw new UserInfoException(WRONG_PASSWORD);

        return userInfo;
    }

    @Transactional(readOnly = true)
    public UserInfoDto getUserInfo(String userEmail) {
        return UserInfoDto.fromEntity(userInfoRepository.findByUserEmail(userEmail));
    }


}

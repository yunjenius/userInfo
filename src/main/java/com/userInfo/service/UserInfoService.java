package com.userInfo.service;

import com.userInfo.dto.NewUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoService {

    @Transactional
    public NewUserInfo.Response createUser(NewUserInfo.Request request) {
        //validateCreateUserRequest(request);
        return null;
    }
}

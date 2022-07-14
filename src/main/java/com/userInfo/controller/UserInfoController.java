package com.userInfo.controller;

import com.userInfo.dto.CreateUserInfo;
import com.userInfo.dto.EditUserInfo;
import com.userInfo.dto.LoginUserInfo;
import com.userInfo.dto.UserInfoDto;
import com.userInfo.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/authUser/{chkOption}/{userPhone}")
    public int authUserPhone(
            @PathVariable final String chkOption,
            @PathVariable final String userPhone
    ) {
        return userInfoService.authUserPhone(chkOption, userPhone);
    }

    @GetMapping("/emailChk/{userEmail}")
    public String checkUserEmail(
            @PathVariable final String userEmail
    ) {
        return userInfoService.checkUserEmail(userEmail);
    }

    @PostMapping("/join")
    public CreateUserInfo.Response createUser(
            @Valid @RequestBody final CreateUserInfo.Request request
    ) {
        return userInfoService.createUser(request);
    }

    @PutMapping("/resetPwd/{userEmail}")
    public UserInfoDto resetUserPwd(
            @PathVariable final String userEmail,
            @Valid @RequestBody final EditUserInfo.Request request
    ) {
        return userInfoService.updateUserPwd(userEmail, request);
    }

    @PostMapping("/login")
    public UserInfoDto loginUser(
            @Valid @RequestBody final LoginUserInfo.Request request
    ) {
        return userInfoService.loginUser(request);
    }

    @GetMapping("/userInfo/{userEmail}")
    public UserInfoDto getUserInfo(
            @PathVariable final String userEmail
    ) {
        return userInfoService.getUserInfo(userEmail);
    }

}

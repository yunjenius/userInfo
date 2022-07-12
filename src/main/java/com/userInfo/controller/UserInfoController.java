package com.userInfo.controller;

import com.userInfo.dto.CreateUserInfo;
import com.userInfo.dto.EditUserInfo;
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

    @PostMapping("/join")
    public CreateUserInfo.Response createUser(
            @Valid @RequestBody final CreateUserInfo.Request request
    ) {
        log.info("Request : {}", request);

        return userInfoService.createUser(request);
    }

    @GetMapping("/userInfo/{userEmail}")
    public UserInfoDto getUserInfo(
            @PathVariable final String userEmail
    ) {
        log.info("GET /userInfo HTTP/1.1");

        return userInfoService.getUserInfo(userEmail);
    }

    @PutMapping("/resetPwd/{userEmail}")
    public UserInfoDto resetUserPwd(
            @PathVariable final String userEmail,
            @Valid @RequestBody final EditUserInfo.Request request
    ) {
        log.info("GET /userInfo HTTP/1.1");

        return userInfoService.resetUserPwd(userEmail, request);
    }
}

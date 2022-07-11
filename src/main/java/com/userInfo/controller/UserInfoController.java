package com.userInfo.controller;

import com.userInfo.dto.NewUserInfo;
import com.userInfo.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @PostMapping("/join")
    public NewUserInfo.Response createUser(
            @Valid @RequestBody final NewUserInfo.Request request
    ) {
        log.info("Request : {}", request);

        return userInfoService.createUser(request);
    }
}

package com.app.user.controller;

import com.app.user.model.UserInfo;
import com.app.user.repository.UserInfoRepository;
import com.app.user.service.UserInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserInfoService userInfoService;

    public UserController(UserInfoService userInfoService){
        this.userInfoService = userInfoService;
    }

    @GetMapping("/getUserId")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getUserId(@RequestParam String username){
            return  userInfoService.getUserIdByUsername(username);
    }

}

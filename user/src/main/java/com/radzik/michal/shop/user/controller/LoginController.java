package com.radzik.michal.shop.user.controller;

import com.radzik.michal.shop.user.domain.dto.LoginDto;
import com.radzik.michal.shop.user.domain.dto.TokenDto;
import com.radzik.michal.shop.user.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public TokenDto login(@RequestBody LoginDto loginDto){
        return loginService.login(loginDto);
    }
}

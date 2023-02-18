package com.radzik.michal.shop.user.service;

import com.radzik.michal.shop.user.domain.dto.LoginDto;
import com.radzik.michal.shop.user.domain.dto.TokenDto;

public interface LoginService {

    TokenDto login(LoginDto loginDto);
}

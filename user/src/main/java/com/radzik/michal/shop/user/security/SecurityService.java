package com.radzik.michal.shop.user.security;


import com.radzik.michal.shop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityService {

    private final UserService userService;

    public boolean hasAccessToUser(Long id) {
        return userService.getCurrentUser().getId().equals(id);
    }

}

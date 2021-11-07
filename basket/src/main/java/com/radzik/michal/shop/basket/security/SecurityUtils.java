package com.radzik.michal.shop.basket.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {

    public static String getCurrentUserEmail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

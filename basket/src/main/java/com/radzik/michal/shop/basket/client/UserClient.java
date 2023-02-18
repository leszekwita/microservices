package com.radzik.michal.shop.basket.client;

import com.radzik.michal.shop.common.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="USER-SERVICE")
//@RequestMapping("/api/users")
public interface UserClient {
    @GetMapping("/api/users/current")
    UserDto getLoggedCurrentUser();
}

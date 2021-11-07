package com.radzik.michal.shop.user.client;

import com.radzik.michal.shop.common.dto.MailDto;
import com.radzik.michal.shop.user.domain.dto.RestartPasswordMailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="NOTIFICATION-SERVICE")
@RequestMapping("/api/email")
public interface NotificationClient {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendEmail (@RequestBody MailDto<RestartPasswordMailDto> mailDto);
}

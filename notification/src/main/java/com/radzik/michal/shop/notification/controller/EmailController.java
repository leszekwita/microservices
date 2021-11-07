package com.radzik.michal.shop.notification.controller;

import com.radzik.michal.shop.common.dto.MailDto;
import com.radzik.michal.shop.notification.service.EmailService;
import com.radzik.michal.shop.notification.service.impl.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
@Slf4j
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public void sendEmail(@RequestBody MailDto<Map<String, Object>> mailDto){
        log.info("mailDto{}", mailDto);
        emailService.sendEmail(mailDto.getTemplateName(), mailDto.getTo(), mailDto.getVariables());
    }

}

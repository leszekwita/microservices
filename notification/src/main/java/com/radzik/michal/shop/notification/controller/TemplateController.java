package com.radzik.michal.shop.notification.controller;

import com.radzik.michal.shop.notification.domain.dto.TemplateDto;
import com.radzik.michal.shop.notification.mapper.TemplateMapper;
import com.radzik.michal.shop.notification.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    private final TemplateMapper mapper;

    @PostMapping
    public TemplateDto saveTemplate(@RequestBody TemplateDto templateDto){
        return mapper.mapToDto(templateService.save(mapper.mapToDao(templateDto)));
    }
    @PutMapping
    public TemplateDto updateTemplate(@RequestBody TemplateDto templateDto){
        return mapper.mapToDto(templateService.update(mapper.mapToDao(templateDto)));
    }
}

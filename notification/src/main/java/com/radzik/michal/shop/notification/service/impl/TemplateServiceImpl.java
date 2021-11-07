package com.radzik.michal.shop.notification.service.impl;

import com.radzik.michal.shop.notification.domain.dao.Template;
import com.radzik.michal.shop.notification.repository.TemplateRepository;
import com.radzik.michal.shop.notification.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;



    @Override
    public Template save(Template template) {
        return templateRepository.save(template);
    }

    @Override
    @Transactional
    public Template update(Template template) {
        Template templateDb = templateRepository.getById(template.getId());
        templateDb.setName(template.getName());
        templateDb.setBody(template.getBody());
        templateDb.setSubject(template.getSubject());
        return templateDb;

    }

    @Override
    public Template findByName(String name) {
        return templateRepository.findByName(name).orElseThrow(()->new EntityNotFoundException("EntityNotFoundException"));
    }
}

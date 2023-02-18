package com.radzik.michal.shop.notification.service;

import com.radzik.michal.shop.notification.domain.dao.Template;

public interface TemplateService {
    Template save(Template template);
    Template update(Template template);
    Template findByName(String name);
}

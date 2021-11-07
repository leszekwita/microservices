package com.radzik.michal.shop.notification.mapper;


import com.radzik.michal.shop.notification.domain.dao.Template;
import com.radzik.michal.shop.notification.domain.dto.TemplateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemplateMapper {

    TemplateDto mapToDto(Template template);

    Template mapToDao(TemplateDto templateDto);
}

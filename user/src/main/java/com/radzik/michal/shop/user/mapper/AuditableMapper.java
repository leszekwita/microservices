package com.radzik.michal.shop.user.mapper;

import com.radzik.michal.shop.common.dto.AuditableDto;
import com.radzik.michal.shop.user.domain.dao.Auditable;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.core.context.SecurityContextHolder;

public interface AuditableMapper <DAO extends Auditable, DTO extends AuditableDto> {

    @AfterMapping
    default void auditableMapping (DAO dao, @MappingTarget DTO.AuditableDtoBuilder<?,?> auditableDtoBuilder) {
        if(!SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            auditableDtoBuilder.lastModifiedDate(null).createdBy(null).lastModifiedBy(null).createdDate(null);
        }

    }
}


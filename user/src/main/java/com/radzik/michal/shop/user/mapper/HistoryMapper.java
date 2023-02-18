package com.radzik.michal.shop.user.mapper;

import com.radzik.michal.shop.common.dto.UserDto;
import com.radzik.michal.shop.user.domain.dao.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.history.Revision;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = AddressMapper.class)
public interface HistoryMapper {
    //@Mapping(source = "entity.email", target = "email")
    @Mapping(source = "entity.email", target = "email")
    @Mapping(source = "entity.firstName", target = "firstName")
    @Mapping(source = "entity.lastName", target = "lastName")
    @Mapping(source = "entity.address", target = "address")
    @Mapping(source = "metadata.revisionType", target = "revType")
    @Mapping(source = "requiredRevisionNumber", target = "operationNumber")
    UserDto toUserDto (Revision<Integer, User> revision);
}

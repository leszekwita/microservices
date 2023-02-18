package com.radzik.michal.shop.user.mapper;

import com.radzik.michal.shop.common.dto.UserDto;

import com.radzik.michal.shop.user.domain.dao.Role;
import com.radzik.michal.shop.user.domain.dao.User;

import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = AddressMapper.class)
public interface UserMapper extends AuditableMapper<User, UserDto> {

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRoles")
    UserDto toDto(User user);

    @Mapping(target = "roles", ignore = true)
    User toDao(UserDto userDto);

    @Named("mapRoles")
    default Set<String> mapRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}

package com.radzik.michal.shop.user.mapper;

import com.radzik.michal.shop.common.dto.UserDto;

import com.radzik.michal.shop.user.domain.dao.User;

import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target="password", ignore = true)
    UserDto toDto (User user);

    User toDao (UserDto userDto);
}

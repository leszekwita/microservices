package com.radzik.michal.shop.common.validator.impl;


import com.radzik.michal.shop.common.dto.UserDto;
import com.radzik.michal.shop.common.validator.SamePassword;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class SamePasswordValidator implements ConstraintValidator<SamePassword, UserDto> {

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }
}

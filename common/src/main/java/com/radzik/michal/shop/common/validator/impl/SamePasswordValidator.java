package com.radzik.michal.shop.common.validator.impl;


import com.radzik.michal.shop.common.dto.UserDto;
import com.radzik.michal.shop.common.validator.SamePassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class SamePasswordValidator implements ConstraintValidator<SamePassword, UserDto> {


    @Override
    public void initialize(SamePassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserDto value, ConstraintValidatorContext context) {
        return value.getPassword().equals(value.getConfirmPassword());
    }

}

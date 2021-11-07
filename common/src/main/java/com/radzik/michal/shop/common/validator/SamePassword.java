package com.radzik.michal.shop.common.validator;

import com.radzik.michal.shop.common.validator.impl.SamePasswordValidator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SamePasswordValidator.class)
public @interface SamePassword {
    String message() default "The passwords are not the same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}


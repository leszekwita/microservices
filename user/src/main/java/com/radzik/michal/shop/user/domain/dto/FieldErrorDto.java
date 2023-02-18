package com.radzik.michal.shop.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErrorDto {

    private String field;

    private String message;
}

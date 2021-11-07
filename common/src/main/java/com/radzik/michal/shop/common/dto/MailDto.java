package com.radzik.michal.shop.common.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailDto<T> {

    private T variables;

    private String to;

    private String templateName;
}

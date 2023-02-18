package com.radzik.michal.shop.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public non-sealed class AddressDto extends AuditableDto {

    private Long id;

    private String name;

    private String streetAddress;

    private String city;

    private String state;

    private String zipCode;

}

package com.radzik.michal.shop.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public non-sealed class ProductDto extends AuditableDto {

    private Long id;

    private String name;

    private Float price;

    private Integer amount;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private String revType;

    private Long operationNumber;

}

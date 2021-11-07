package com.radzik.michal.shop.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private Integer price;

    private Integer amount;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

}

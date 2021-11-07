package com.radzik.michal.shop.basket.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder(toBuilder = true)
public class Product {

    private Long id;

    private String name;

    private Integer price;

    private Integer amount;


}

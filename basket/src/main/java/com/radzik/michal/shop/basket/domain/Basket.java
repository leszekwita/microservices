package com.radzik.michal.shop.basket.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Builder
@Getter
@Setter
@Document(indexName = "basket")
public class Basket {

    @Id
    private String id;

    private String email;

    @Singular
    private List<Product> products;
}

package com.radzik.michal.shop.statistic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Document(indexName = "statistic-basket")
public class StatisticBasket {

    @Id
    private String id;

    private String email;

    private Long productId;

    private Float price;

    private Integer amount;

    private LocalDateTime addDate;

   // @Singular
    //private List<Product> products;
}
